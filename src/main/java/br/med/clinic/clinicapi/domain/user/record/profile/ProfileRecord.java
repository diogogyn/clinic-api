package br.med.clinic.clinicapi.domain.user.record.profile;


import br.med.clinic.clinicapi.domain.user.Profile;
import jakarta.validation.constraints.NotBlank;

public record ProfileRecord(@NotBlank String profileName) {
    public ProfileRecord (Profile profile){
        this(profile.getName());
    }
}
