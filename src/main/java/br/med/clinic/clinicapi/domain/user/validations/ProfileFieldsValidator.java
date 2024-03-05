package br.med.clinic.clinicapi.domain.user.validations;
import br.med.clinic.clinicapi.domain.user.record.profile.UserProfileRecord;
import br.med.clinic.clinicapi.domain.user.repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileFieldsValidator implements UserProfileValidator {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(UserProfileRecord record) {
//        if(record.userId() == null)
//            throw new ValidationException("ID do usuário não foi informado");
        if(record.rolesId().isEmpty()){
            throw new ValidationException("Nenhuma Profile ID para o usuário não foi informado");
        }
//        if(this.userRepository.existsById(record.userId()))
//            throw new ValidationException("Usuário não foi encontrado pelo ID informado");

    }
}
