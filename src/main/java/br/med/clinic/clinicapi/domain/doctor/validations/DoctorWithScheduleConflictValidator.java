package br.med.clinic.clinicapi.domain.doctor.validations;

import br.med.clinic.clinicapi.domain.appointment.record.ScheduleAppointmentRecord;
import br.med.clinic.clinicapi.domain.appointment.repository.AppointmentRepository;
import jakarta.validation.ValidationException;

public class DoctorWithScheduleConflictValidator {

    private AppointmentRepository appointmentRepository;
    public void validate(ScheduleAppointmentRecord record){
        Boolean doctorWithConflict = this.appointmentRepository.existsByDoctorIdAndDate(record.idDoctor(), record.date());
        if(doctorWithConflict)
            throw new ValidationException("Medico já possui outra consulta agendada neste mesmo horario");
    }
}
