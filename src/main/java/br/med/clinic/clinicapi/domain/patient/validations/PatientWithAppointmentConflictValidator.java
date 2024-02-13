package br.med.clinic.clinicapi.domain.patient.validations;

import br.med.clinic.clinicapi.domain.appointment.record.ScheduleAppointmentRecord;
import br.med.clinic.clinicapi.domain.appointment.repository.AppointmentRepository;
import jakarta.validation.ValidationException;

import java.time.LocalDateTime;

public class PatientWithAppointmentConflictValidator {
    private AppointmentRepository appointmentRepository;
    public void validate(ScheduleAppointmentRecord record){
        LocalDateTime firstTime = record.date().withHour(7);
        LocalDateTime lastTime = record.date().withHour(18);

        Boolean conflictApppointment = this.appointmentRepository.existsByPatientIdAndDateBetween(record.idPatient(), firstTime, lastTime);
        if(conflictApppointment)
            throw new ValidationException("Paciente já possui uma consulta agendada neste dia");
    }

}
