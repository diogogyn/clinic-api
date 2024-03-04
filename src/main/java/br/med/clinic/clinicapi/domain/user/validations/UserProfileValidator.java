package br.med.clinic.clinicapi.domain.user.validations;

import br.med.clinic.clinicapi.domain.user.record.profile.UserProfileRecord;

public interface UserProfileValidator {
    void validate(UserProfileRecord record);
}