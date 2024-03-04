package br.med.clinic.clinicapi.domain.user.record;

import jakarta.validation.constraints.NotNull;

public record UserUpdateRecord(
        @NotNull
        Long id,
        String login,
        String password
) {
}
