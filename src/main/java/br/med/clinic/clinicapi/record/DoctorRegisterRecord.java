package br.med.clinic.clinicapi.record;

import br.med.clinic.clinicapi.enums.Speciality;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record DoctorRegisterRecord(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Speciality speciality,
        @NotNull
        @Valid
        AddressRecord address) {
}
