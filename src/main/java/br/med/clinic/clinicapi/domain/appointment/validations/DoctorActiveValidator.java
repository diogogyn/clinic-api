package br.med.clinic.clinicapi.domain.appointment.validations;

import br.med.clinic.clinicapi.domain.appointment.record.ScheduleAppointmentRecord;
import br.med.clinic.clinicapi.domain.doctor.repository.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorActiveValidator implements ScheduleAppointmentValidator {
    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(ScheduleAppointmentRecord record){
        if(record.idDoctor() == null)
            return;
        Boolean doctorIsActive = this.doctorRepository.findActiveById(record.idDoctor());
        if(!doctorIsActive)
            throw new ValidationException("Consulta não pode ser agendada com medico inativo");
    }
}
