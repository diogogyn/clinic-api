package br.med.clinic.clinicapi.domain.user.validations;

import br.med.clinic.clinicapi.domain.user.record.UserRegisterRecord;

public interface UserValidator {
    void validate(UserRegisterRecord record);
    void validateIfNotExist(Long userId);
}
