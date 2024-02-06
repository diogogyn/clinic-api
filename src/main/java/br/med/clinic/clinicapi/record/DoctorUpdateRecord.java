package br.med.clinic.clinicapi.record;

import jakarta.validation.constraints.NotNull;

public record DoctorUpdateRecord (
                                @NotNull
                                Long id,
                                String name,
                                String phone,
                                AddressRecord address) {
}
