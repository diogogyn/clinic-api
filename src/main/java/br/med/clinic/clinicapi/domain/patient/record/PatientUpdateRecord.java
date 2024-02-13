package br.med.clinic.clinicapi.domain.patient.record;

import br.med.clinic.clinicapi.domain.address.record.AddressRecord;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateRecord(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressRecord address
) {
}
