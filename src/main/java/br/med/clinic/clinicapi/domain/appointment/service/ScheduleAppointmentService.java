package br.med.clinic.clinicapi.domain.appointment.service;


import br.med.clinic.clinicapi.domain.appointment.Appointment;
import br.med.clinic.clinicapi.domain.appointment.record.ScheduleAppointmentRecord;
import br.med.clinic.clinicapi.domain.appointment.repository.AppointmentRepository;
import br.med.clinic.clinicapi.domain.doctor.Doctor;
import br.med.clinic.clinicapi.domain.doctor.enums.Speciality;
import br.med.clinic.clinicapi.domain.doctor.repository.DoctorRepository;
import br.med.clinic.clinicapi.domain.patient.Patient;
import br.med.clinic.clinicapi.domain.patient.repository.PatientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ScheduleAppointmentService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    public void schedule(ScheduleAppointmentRecord record){
        if(!this.patientRepository.existsById(record.idPatient())){
            throw new ValidationException("ID do paciente informado não existe");
        }
        if(record.idDoctor() != null && !this.doctorRepository.existsById(record.idPatient())){
            throw new ValidationException("ID do medico informado não existe");
        }
        Patient patient = this.patientRepository.getReferenceById(record.idPatient());
        Doctor doctor = this.chooseDoctor(record);
        //Doctor doctor = this.doctorRepository.findById(record.idDoctor()).get();
        Appointment appointment = new Appointment(null, doctor, patient, record.date());
        this.appointmentRepository.save(appointment);
    }

    private Doctor chooseDoctor(ScheduleAppointmentRecord record) {
        if(record.idDoctor() != null)
            return this.doctorRepository.getReferenceById(record.idDoctor());
        if(record.speciality() == null)
            throw new ValidationException("Especialidade é obrigatoria, quando médico não for escolhido!");

        return this.doctorRepository.chooseDoctorRandomFreeOnDate(record.speciality(), record.date());
    }
}
