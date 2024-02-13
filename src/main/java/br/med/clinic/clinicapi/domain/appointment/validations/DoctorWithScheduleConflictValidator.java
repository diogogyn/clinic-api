package br.med.clinic.clinicapi.domain.appointment.validations;

import br.med.clinic.clinicapi.domain.appointment.record.ScheduleAppointmentRecord;
import br.med.clinic.clinicapi.domain.appointment.repository.AppointmentRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithScheduleConflictValidator implements ScheduleAppointmentValidator {
    @Autowired
    private AppointmentRepository appointmentRepository;
    public void validate(ScheduleAppointmentRecord record){
        Boolean doctorWithConflict = this.appointmentRepository.existsByDoctorIdAndDate(record.idDoctor(), record.date());
        if(doctorWithConflict)
            throw new ValidationException("Medico já possui outra consulta agendada neste mesmo horario");
    }
}
