package br.med.clinic.clinicapi.domain.appointment.record;

import java.time.LocalDateTime;

public record AppointmentDetailsRecord(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {
}
