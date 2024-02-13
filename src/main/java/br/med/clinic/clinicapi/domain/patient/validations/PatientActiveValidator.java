package br.med.clinic.clinicapi.domain.patient.validations;

import br.med.clinic.clinicapi.domain.appointment.record.ScheduleAppointmentRecord;
import br.med.clinic.clinicapi.domain.patient.repository.PatientRepository;
import jakarta.validation.ValidationException;

public class PatientActiveValidator {
    private PatientRepository patientRepository;
    public void validate(ScheduleAppointmentRecord record){
        if(record.idPatient() == null)
            return;
        Boolean doctorIsActive = this.patientRepository.findActiveById(record.idPatient());
        if(!doctorIsActive)
            throw new ValidationException("Consulta não pode ser agendada com paciente inativo");
    }
}
