package br.med.clinic.clinicapi.infra.administration.repository;

import br.med.clinic.clinicapi.domain.user.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository  extends JpaRepository<Profile, Long> {

    Boolean existsByName(String name);
    Profile getReferenceByName(String name);
}