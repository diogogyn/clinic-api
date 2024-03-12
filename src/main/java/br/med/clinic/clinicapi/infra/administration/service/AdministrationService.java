package br.med.clinic.clinicapi.infra.administration.service;

import br.med.clinic.clinicapi.domain.http.ViaCepResponse;
import br.med.clinic.clinicapi.domain.user.Profile;
import br.med.clinic.clinicapi.domain.user.User;
import br.med.clinic.clinicapi.domain.user.UserProfile;
import br.med.clinic.clinicapi.domain.user.record.UserRegisterRecord;
import br.med.clinic.clinicapi.domain.user.record.UserUpdateRecord;
import br.med.clinic.clinicapi.domain.user.record.profile.ProfileRecord;
import br.med.clinic.clinicapi.domain.user.record.profile.UserProfileRecord;
import br.med.clinic.clinicapi.domain.user.repository.UserRepository;
import br.med.clinic.clinicapi.domain.user.validations.UserProfileValidator;
import br.med.clinic.clinicapi.domain.user.validations.UserValidator;
import br.med.clinic.clinicapi.http.ViaCepClient;
import br.med.clinic.clinicapi.infra.administration.repository.ProfileRepository;
import br.med.clinic.clinicapi.infra.administration.repository.UserProfileRepository;
import br.med.clinic.clinicapi.infra.security.BCryptPasswordEncript;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
@Service
public class AdministrationService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ViaCepClient viaCepClient;

    @Autowired
    private BCryptPasswordEncript encript;
    @Autowired
    private List<UserProfileValidator> userProfileValidatorList;
    @Autowired
    private List<UserValidator> userValidatorList;

    @Value("${api.user.profile.default}")
    private String defaultUserProfile;
    @Transactional
    public User createUser(UserRegisterRecord record) {
        this.userValidatorList.forEach(v -> v.validate(record));

        User user = new User();
        user.setLogin(record.login());
        String passwordEncrypted = this.encript.encriptyPassword(record.password());
        user.setSenha(passwordEncrypted);
        user.setDeafaultSettings();

        User save = this.userRepository.save(user);

        List<Long> roles = new ArrayList<>();
        if(record.roles() == null || record.roles().isEmpty()){
            Profile profile = this.profileRepository.getReferenceByName(defaultUserProfile);
            if(profile == null)
                throw new ValidationException("Profile padrão escolhido para este usuário não eixste. Contacte o administrador ou selecione um perfil para este usuário");
            roles.add(profile.getId());
        } else {
            roles = record.roles();
        }

        this.addProfileToUser(save.getId(), roles);
        this.userRepository.flush();
        return save;
    }

    public void deleteAllOfUser(Long userId){
        this.userValidatorList.forEach(v -> v.validateIfNotExist(userId));

        User referenceById = this.userRepository.getReferenceById(userId);
        List<UserProfile> referenceByUserId = this.userProfileRepository.getReferenceByUserId(userId);

        this.userProfileRepository.deleteAll(referenceByUserId);
        this.userRepository.delete(referenceById);
    }

    @Transactional
    public void updateUserPassword(UserUpdateRecord record) {
        this.userValidatorList.forEach(v -> v.validateIfNotExist(record.id()));
        User user = this.userRepository.findById(record.id()).get();
        if(record.password() != null && !record.password().isEmpty()) {
            String passwordEncrypted = this.encript.encriptyPassword(record.password());
            user.setSenha(passwordEncrypted);
        }
    }

    @Transactional
    public void disableUser(Long userId){
        this.userValidatorList.forEach(v -> v.validateIfNotExist(userId));
        User user = this.userRepository.findById(userId).get();
        user.setActive(false);
    }

    public Profile createProfile(ProfileRecord record){
        if(this.profileRepository.existsByName(record.profileName().toUpperCase())){
            throw new ValidationException("Profile já existe no banco");
        }
        Profile profile = new Profile(null, record.profileName());
        return this.profileRepository.save(profile);
    }

    public void addProfileToUser(UserProfileRecord record){
        this.userValidatorList.forEach(v -> v.validateIfNotExist(record.userId()));
        this.userProfileValidatorList.forEach(v -> v.validate(record));
        this.addProfileToUser(record.userId(), record.rolesId());
    }

    public void deleteProfileFromUser(UserProfileRecord record){
        this.userValidatorList.forEach(v -> v.validateIfNotExist(record.userId()));
        this.userProfileValidatorList.forEach(v -> v.validate(record));
        this.deleteProfileToUser(record.userId(), record.rolesId());
    }

    public void updateProfileFromUser(UserProfileRecord record){
        if(record.rolesId() == null) return;
        this.userValidatorList.forEach(v -> v.validateIfNotExist(record.userId()));
        this.userProfileValidatorList.forEach(v -> v.validate(record));
        User user = this.userRepository.getReferenceById(record.userId());
        List<Long> currentProfiles = user.getPerfis().stream()
                .map(Profile::getId)
                .toList();
        List<Long> profilesToAdd = record.rolesId().stream()
                .filter(phone -> !currentProfiles.contains(phone))
                .toList();
        List<Long> profilesToDelete = currentProfiles.stream()
                .filter(phone -> !record.rolesId().contains(phone))
                .toList();
        if(!profilesToAdd.isEmpty()) this.addProfileToUser(record.userId(), profilesToAdd);
        if(!profilesToDelete.isEmpty()) this.deleteProfileToUser(record.userId(), profilesToDelete);
    }

    public ViaCepResponse getAddress(@PathVariable String cep){
        return this.viaCepClient.getAddress(cep);
    }

    private void deleteProfileToUser(Long userId, List<Long> roles){
        for(Long role : roles){
            UserProfile referenceByUserIdAndProfileId = this.userProfileRepository.getReferenceByUserIdAndProfileId(userId, role);
            if(referenceByUserIdAndProfileId != null)
                this.userProfileRepository.delete(referenceByUserIdAndProfileId);
        }
    }

    private void addProfileToUser(Long userId, List<Long> roles){
        for(Long role : roles){
            if(!this.userProfileRepository.existsByUserIdAndProfileId(userId, role)){
                UserProfile up = new UserProfile(null, userId, role);
                this.userProfileRepository.save(up);
            }
        }
    }
}
