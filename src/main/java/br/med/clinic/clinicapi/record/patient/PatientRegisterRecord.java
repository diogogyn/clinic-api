package br.med.clinic.clinicapi.record.patient;

import br.med.clinic.clinicapi.record.address.AddressRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatientRegisterRecord(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,

        @NotNull @Valid AddressRecord address
) {
}
