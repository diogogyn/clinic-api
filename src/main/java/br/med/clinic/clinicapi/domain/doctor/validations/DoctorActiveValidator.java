package br.med.clinic.clinicapi.domain.doctor.validations;

import br.med.clinic.clinicapi.domain.appointment.record.ScheduleAppointmentRecord;
import br.med.clinic.clinicapi.domain.doctor.repository.DoctorRepository;
import jakarta.validation.ValidationException;

public class DoctorActiveValidator {
    private DoctorRepository doctorRepository;

    public void validate(ScheduleAppointmentRecord record){
        if(record.idDoctor() == null)
            return;
        Boolean doctorIsActive = this.doctorRepository.findActiveById(record.idDoctor());
        if(!doctorIsActive)
            throw new ValidationException("Consulta não pode ser agendada com medico inativo");
    }
}
