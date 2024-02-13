package br.med.clinic.clinicapi.record.doctor;

import br.med.clinic.clinicapi.record.address.AddressRecord;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateRecord (
                                @NotNull
                                Long id,
                                String name,
                                String phone,
                                AddressRecord address) {
}
