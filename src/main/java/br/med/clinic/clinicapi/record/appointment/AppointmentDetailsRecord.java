package br.med.clinic.clinicapi.record.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailsRecord(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {
}
