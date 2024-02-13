package br.med.clinic.clinicapi.domain.doctor.record;

import br.med.clinic.clinicapi.domain.address.record.AddressRecord;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateRecord (
                                @NotNull
                                Long id,
                                String name,
                                String phone,
                                AddressRecord address) {
}
