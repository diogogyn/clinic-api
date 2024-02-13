package br.med.clinic.clinicapi.domain.appointment.validations;

import br.med.clinic.clinicapi.domain.appointment.record.ScheduleAppointmentRecord;
import br.med.clinic.clinicapi.domain.appointment.repository.AppointmentRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PatientWithAppointmentConflictValidator implements ScheduleAppointmentValidator {
    @Autowired
    private AppointmentRepository appointmentRepository;
    public void validate(ScheduleAppointmentRecord record){
        LocalDateTime firstTime = record.date().withHour(7);
        LocalDateTime lastTime = record.date().withHour(18);

        Boolean conflictApppointment = this.appointmentRepository.existsByPatientIdAndDateBetween(record.idPatient(), firstTime, lastTime);
        if(conflictApppointment)
            throw new ValidationException("Paciente já possui uma consulta agendada neste dia");
    }

}
