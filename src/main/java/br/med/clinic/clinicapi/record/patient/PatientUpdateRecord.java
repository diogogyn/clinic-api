package br.med.clinic.clinicapi.record.patient;

import br.med.clinic.clinicapi.record.AddressRecord;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateRecord(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressRecord address
) {
}
