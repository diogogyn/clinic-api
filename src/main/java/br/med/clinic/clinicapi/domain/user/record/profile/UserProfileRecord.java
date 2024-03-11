package br.med.clinic.clinicapi.domain.user.record.profile;

import br.med.clinic.clinicapi.domain.user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserProfileRecord(@NotNull Long userId, @NotNull @NotEmpty List<Long> rolesId) {
    public UserProfileRecord(User user, List<Long> roles){
        this(user.getId(), roles);
    }
}