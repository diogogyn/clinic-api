package br.med.clinic.clinicapi.domain.appointment.validations;

import br.med.clinic.clinicapi.domain.appointment.record.ScheduleAppointmentRecord;

public interface ScheduleAppointmentValidator {

    void validate(ScheduleAppointmentRecord record);
}
