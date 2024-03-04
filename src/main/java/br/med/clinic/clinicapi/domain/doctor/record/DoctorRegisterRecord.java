package br.med.clinic.clinicapi.domain.doctor.record;

import br.med.clinic.clinicapi.domain.address.record.AddressRecord;
import br.med.clinic.clinicapi.domain.doctor.enums.Speciality;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorRegisterRecord(
        @NotBlank
        String name,
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato do email é inválido")
        String email,
        @NotBlank(message = "Telefone é obrigatório")
        String phone,
        @NotBlank(message = "CRM é obrigatório")
        @Pattern(regexp = "\\d{4,6}", message = "Formato do CRM é inválido")
        String crm,
        @NotNull
        Speciality speciality,
        @NotNull
        @Valid
        AddressRecord address) {
}
