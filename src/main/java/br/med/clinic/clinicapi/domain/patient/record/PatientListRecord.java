package br.med.clinic.clinicapi.domain.patient.record;

import br.med.clinic.clinicapi.domain.patient.Patient;

public record PatientListRecord(Long id, String name, String email, String cpf) {
    public PatientListRecord(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
