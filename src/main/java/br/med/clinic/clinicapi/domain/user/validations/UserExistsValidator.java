package br.med.clinic.clinicapi.domain.user.validations;

import br.med.clinic.clinicapi.domain.user.record.UserRegisterRecord;
import br.med.clinic.clinicapi.domain.user.repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserExistsValidator implements UserValidator {
    @Autowired
    private UserRepository repository;
    @Override
    public void validate(UserRegisterRecord record) {
//        if(this.repository.findByLogin(record.login()) != null){
//            throw new ValidationException("Já existe um usuario cadastrado com este login no sistema. Experimente Recuperar o acesso ou atualizar o cadastro");
//        }
    }

    @Override
    public void validateIfNotExist(Long userId) {
        if(userId == null)
            throw new ValidationException("ID do usuário não foi informado.");
        boolean userExist = this.repository.existsById(userId);
        if(!userExist)
            throw new ValidationException("Usuário não foi encontrado pelo ID informado");
    }
}
