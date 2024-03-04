package br.med.clinic.clinicapi.domain.user.record.profile;

import br.med.clinic.clinicapi.domain.user.User;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserProfileRecord(@NotBlank Long userId, @NotBlank List<Long> rolesId) {
    public UserProfileRecord(User user, List<Long> roles){
        this(user.getId(), roles);
    }
}