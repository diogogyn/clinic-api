package br.med.clinic.clinicapi.domain.user.record;
import br.med.clinic.clinicapi.domain.user.User;

public record UserListRecord(
        Long id,
        String login
) {
    public UserListRecord(User user) {
        this(user.getId(), user.getLogin());
    }
}
