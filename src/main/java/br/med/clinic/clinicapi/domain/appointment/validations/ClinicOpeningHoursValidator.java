package br.med.clinic.clinicapi.domain.appointment.validations;

import br.med.clinic.clinicapi.domain.appointment.record.ScheduleAppointmentRecord;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ClinicOpeningHoursValidator implements ScheduleAppointmentValidator {

    public void validate(ScheduleAppointmentRecord record){
        LocalDateTime appointmentDate = record.date();
        boolean sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean beforeOpeningClinic = appointmentDate.getHour() < 7;
        boolean afterClosingClinic = appointmentDate.getHour() > 18;
        if(sunday || beforeOpeningClinic || afterClosingClinic)
            throw new ValidationException("Consulta fora do horario de funcionamento da clinica");
    }
}
