package br.med.clinic.clinicapi.controller;

import br.med.clinic.clinicapi.domain.http.ViaCepResponse;
import br.med.clinic.clinicapi.domain.user.Profile;
import br.med.clinic.clinicapi.domain.user.User;
import br.med.clinic.clinicapi.domain.user.record.UserDetailRecord;
import br.med.clinic.clinicapi.domain.user.record.UserListRecord;
import br.med.clinic.clinicapi.domain.user.record.UserRegisterRecord;
import br.med.clinic.clinicapi.domain.user.record.UserUpdateRecord;
import br.med.clinic.clinicapi.domain.user.record.profile.ProfileDetailsRecord;
import br.med.clinic.clinicapi.domain.user.record.profile.ProfileRecord;
import br.med.clinic.clinicapi.domain.user.record.profile.UserProfileRecord;
import br.med.clinic.clinicapi.domain.user.repository.UserRepository;
import br.med.clinic.clinicapi.http.ViaCepClient;
import br.med.clinic.clinicapi.infra.administration.repository.ProfileRepository;
import br.med.clinic.clinicapi.infra.administration.service.AdministrationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("admin")
@SecurityRequirement(name = "bearer-key")
public class AdministrationController {

    @Autowired
    private AdministrationService adminService;

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;



    @PostMapping("/user")
    @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity create(@RequestBody @Valid UserRegisterRecord record, UriComponentsBuilder uriBuilder) {

        User save = this.adminService.createUser(record);
        User user = this.userRepository.getReferenceById(save.getId());
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDetailRecord(user));
    }

    @PutMapping("/user/disable/{id}")
    @Transactional
    @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity disableUser(@PathVariable Long id){
        this.adminService.disableUser(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{id}")
    @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity deleteAllOfUser(@PathVariable Long id){
        this.adminService.deleteAllOfUser(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/user/password")
    @Transactional
    @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity updateUserPassword(@RequestBody @Valid UserUpdateRecord record){
        this.adminService.updateUserPassword(record);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user")
    @Cacheable(value = "listAllUsers")
    public ResponseEntity<Page<UserListRecord>> listAllUsers(@PageableDefault(size = 10, sort = {"login"}) Pageable paginacao) {
        var page = userRepository.findAll(paginacao).map(UserListRecord::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/user/{id}")
    @Cacheable(value = "listAllUsers")
    public ResponseEntity<UserDetailRecord> detailUser(@PathVariable Long id) {
        var user = userRepository.getReferenceById(id);
        return ResponseEntity.ok(new UserDetailRecord(user));
    }

    @PostMapping("/user/profile")
    @Transactional
    @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity addProfileToUser(@RequestBody @Valid UserProfileRecord record, UriComponentsBuilder uriBuilder) {
        this.adminService.addProfileToUser(record);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/user/profile")
    @Transactional
    @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity deleteProfileFromUser(@RequestBody @Valid UserProfileRecord record, UriComponentsBuilder uriBuilder) {
        this.adminService.deleteProfileFromUser(record);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/user/profile")
    @Transactional
    @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity updateProfileFromUser(@RequestBody @Valid UserProfileRecord record, UriComponentsBuilder uriBuilder) {
        this.adminService.deleteProfileFromUser(record);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile")
    @Cacheable(value = "listAllUsers")
    public ResponseEntity listAllProfiles(){
        return ResponseEntity.ok(this.profileRepository.findAll().stream().map(ProfileDetailsRecord::new));
    }

    @PostMapping("/profile")
    @Transactional
    @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity createProfile(@RequestBody @Valid ProfileRecord record, UriComponentsBuilder uriBuilder) {
        Profile save = this.adminService.createProfile(record);
        URI uri = uriBuilder.path("/profile/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProfileDetailsRecord(save));
    }

    @GetMapping("/profile/{id}")
    @Cacheable(value = "listAllUsers")
    public ResponseEntity<ProfileDetailsRecord> detailProfile(@PathVariable Long id) {
        var profile = this.profileRepository.getReferenceById(id);
        return ResponseEntity.ok(new ProfileDetailsRecord(profile));
    }
    @GetMapping("/address/{cep}")
    public ResponseEntity<ViaCepResponse> detailProfile(@PathVariable String cep) {
        ViaCepResponse address = this.adminService.getAddress(cep);
        return ResponseEntity.ok(address);
    }
}
