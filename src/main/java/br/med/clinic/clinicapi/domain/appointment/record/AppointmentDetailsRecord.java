package br.med.clinic.clinicapi.domain.appointment.record;

import br.med.clinic.clinicapi.domain.appointment.Appointment;

import java.time.LocalDateTime;

public record AppointmentDetailsRecord(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {
    public AppointmentDetailsRecord(Appointment save) {
        this(save.getId(), save.getDoctor().getId(), save.getPatient().getId(), save.getDate());
    }
}
