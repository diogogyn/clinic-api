package br.med.clinic.clinicapi.domain.user.record.profile;

import br.med.clinic.clinicapi.domain.user.Profile;

public record ProfileDetailsRecord(Long id, String name) {
    public ProfileDetailsRecord(Profile profile){
        this(profile.getId(), profile.getName());
    }
}