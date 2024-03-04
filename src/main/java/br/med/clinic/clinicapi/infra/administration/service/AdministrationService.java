package br.med.clinic.clinicapi.infra.administration.service;

import br.med.clinic.clinicapi.domain.user.Profile;
import br.med.clinic.clinicapi.domain.user.User;
import br.med.clinic.clinicapi.domain.user.UserProfile;
import br.med.clinic.clinicapi.domain.user.record.UserRegisterRecord;
import br.med.clinic.clinicapi.domain.user.record.profile.ProfileRecord;
import br.med.clinic.clinicapi.domain.user.record.profile.UserProfileRecord;
import br.med.clinic.clinicapi.domain.user.repository.UserRepository;
import br.med.clinic.clinicapi.domain.user.validations.UserProfileValidator;
import br.med.clinic.clinicapi.domain.user.validations.UserValidator;
import br.med.clinic.clinicapi.infra.administration.repository.ProfileRepository;
import br.med.clinic.clinicapi.infra.administration.repository.UserProfileRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private List<UserProfileValidator> userProfileValidatorList;
    @Autowired
    private List<UserValidator> userValidatorList;


    public User createUser(UserRegisterRecord record) {
        List<Long> roles = null;
        this.userValidatorList.forEach(v -> v.validate(record));

        User user = new User();
        user.setLogin(record.login());
        user.setSenha(record.password());
        User save = this.userRepository.save(user);

        if(record.roles().isEmpty()){
            Profile profile = this.profileRepository.getReferenceByName("USER");
            roles = new ArrayList<>();
            roles.add(profile.getId());
        } else {
            roles = record.roles();
        }

        this.addProfileToUser(new UserProfileRecord(save, roles));

        return save;
    }

    public void deleteAllOfUser(Long userId){
        this.userValidatorList.forEach(v -> v.validateIfNotExist(userId));

        User referenceById = this.userRepository.getReferenceById(userId);
        List<UserProfile> referenceByUserId = this.userProfileRepository.getReferenceByUserId(userId);

        this.userProfileRepository.deleteAll(referenceByUserId);
        this.userRepository.delete(referenceById);
    }

    public void updateUser(UserRegisterRecord record) {

    }

    public Profile createProfile(ProfileRecord record){
        if(this.profileRepository.existsByName(record.profileName().toUpperCase())){
            throw new ValidationException("Profile já existe no banco");
        }
        Profile profile = new Profile(null, record.profileName());
        return this.profileRepository.save(profile);
    }

    public void addProfileToUser(UserProfileRecord record){
        this.userProfileValidatorList.forEach(v -> v.validate(record));
        this.addProfileToUser(record.userId(), record.rolesId());
    }

    public void deleteProfileFromUser(UserProfileRecord record){
        this.userProfileValidatorList.forEach(v -> v.validate(record));

        for(Long role : record.rolesId()){
            UserProfile referenceByUserIdAndProfileId = this.userProfileRepository.getReferenceByUserIdAndProfileId(record.userId(), role);
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
