package br.med.clinic.clinicapi.record.patient;

import br.med.clinic.clinicapi.domain.address.Address;
import br.med.clinic.clinicapi.domain.patient.Patient;

public record PatientDetailsRecord(Long id, String name, String email, String cpf, String phone, Address address) {
    public PatientDetailsRecord(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf(), patient.getPhone(), patient.getAddress());
    }
}
