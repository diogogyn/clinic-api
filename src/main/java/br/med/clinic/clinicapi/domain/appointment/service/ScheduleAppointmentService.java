package br.med.clinic.clinicapi.domain.appointment.service;


import br.med.clinic.clinicapi.domain.appointment.Appointment;
import br.med.clinic.clinicapi.domain.appointment.record.AppointmentDetailsRecord;
import br.med.clinic.clinicapi.domain.appointment.record.ScheduleAppointmentRecord;
import br.med.clinic.clinicapi.domain.appointment.repository.AppointmentRepository;
import br.med.clinic.clinicapi.domain.appointment.validations.ScheduleAppointmentValidator;
import br.med.clinic.clinicapi.domain.doctor.Doctor;
import br.med.clinic.clinicapi.domain.doctor.repository.DoctorRepository;
import br.med.clinic.clinicapi.domain.patient.Patient;
import br.med.clinic.clinicapi.domain.patient.repository.PatientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleAppointmentService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private List<ScheduleAppointmentValidator> validatorList;

    public AppointmentDetailsRecord schedule(ScheduleAppointmentRecord record){
        if(!this.patientRepository.existsById(record.idPatient())){
            throw new ValidationException("ID do paciente informado não existe");
        }
        if(record.idDoctor() != null && !this.doctorRepository.existsById(record.idDoctor())){
            throw new ValidationException("ID do medico informado não existe");
        }

        this.validatorList.forEach(v -> v.validate(record));

        Patient patient = this.patientRepository.getReferenceById(record.idPatient());
        Doctor doctor = this.chooseDoctor(record);
        if(doctor == null)
            throw new ValidationException("Não existe medico disponivel nesta data");
        Appointment appointment = new Appointment(null, doctor, patient, record.date());
        Appointment save = this.appointmentRepository.save(appointment);

        return new AppointmentDetailsRecord(save);
    }

    private Doctor chooseDoctor(ScheduleAppointmentRecord record) {
        if(record.idDoctor() != null)
            return this.doctorRepository.getReferenceById(record.idDoctor());
        if(record.speciality() == null)
            throw new ValidationException("Especialidade é obrigatoria, quando médico não for escolhido!");

        return this.doctorRepository.chooseDoctorRandomFreeOnDate(record.speciality(), record.date());
    }
}
