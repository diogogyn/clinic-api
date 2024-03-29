package br.med.clinic.clinicapi.domain.appointment.validations;

import br.med.clinic.clinicapi.domain.appointment.record.ScheduleAppointmentRecord;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceScheduleValidator implements ScheduleAppointmentValidator {
    public void validate(ScheduleAppointmentRecord record){
        LocalDateTime appointmentDate = record.date();
        LocalDateTime now = LocalDateTime.now();
        long differenceInMunites = Duration.between(now, appointmentDate).toMinutes();
        if(differenceInMunites < 30){
            throw new ValidationException("Consulta deve ser agendada com antecedencia minima de 30 minutos");
        }
    }
}
