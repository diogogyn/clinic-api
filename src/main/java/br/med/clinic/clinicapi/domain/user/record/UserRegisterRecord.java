package br.med.clinic.clinicapi.domain.user.record;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserRegisterRecord(
        Long id,
        @NotBlank
        @Email
        String login,
        @NotBlank
        String password,
        List<Long> roles
) {
}