package br.med.clinic.clinicapi.record.doctor;

import br.med.clinic.clinicapi.enums.Speciality;
import br.med.clinic.clinicapi.record.AddressRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

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
