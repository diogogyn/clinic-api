package br.med.clinic.clinicapi.domain.user.record;

import br.med.clinic.clinicapi.domain.user.Profile;
import br.med.clinic.clinicapi.domain.user.User;

import java.util.List;

public record UserDetailRecord(
        Long id,
        String login,
        List<Profile> profiles,
        Boolean isAccountNonExpired,
        Boolean isAccountNonLocked,
        Boolean isCredentialsNonExpired,
        Boolean isEnabled
) {
    public UserDetailRecord(User user) {
        this(user.getId(), user.getLogin(), user.getPerfis(), user.isAccountNonExpired(), user.isAccountNonLocked(), user.isCredentialsNonExpired(), user.isEnabled());
    }
}