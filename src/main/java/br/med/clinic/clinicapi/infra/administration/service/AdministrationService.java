package br.med.clinic.clinicapi.infra.administration.service;

import br.med.clinic.clinicapi.domain.user.repository.UserRepository;
import br.med.clinic.clinicapi.infra.administration.repository.ProfileRepository;
import br.med.clinic.clinicapi.infra.administration.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministrationService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private List<UserProfileValidator> userProfileValidatorList;
//    @Autowired
//    private List<UserValidator> userValidatorList;


}
