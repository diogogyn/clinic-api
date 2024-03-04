package br.med.clinic.clinicapi.domain.user.validations;
import br.med.clinic.clinicapi.domain.user.record.profile.UserProfileRecord;
import br.med.clinic.clinicapi.infra.administration.repository.ProfileRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileExistsValidator implements UserProfileValidator {
    @Autowired
    private ProfileRepository repository;

    @Override
    public void validate(UserProfileRecord record) {
        if(!record.rolesId().isEmpty()){
            for(Long role : record.rolesId()) {
                if(!this.repository.existsById(role))
                    throw new ValidationException("ID: " + role + " não está cadastrado no banco");
            }
        }
    }
}

