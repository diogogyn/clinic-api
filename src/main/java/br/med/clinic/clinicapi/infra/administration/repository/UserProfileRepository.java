package br.med.clinic.clinicapi.infra.administration.repository;
import br.med.clinic.clinicapi.domain.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Boolean existsByUserIdAndProfileId(Long userId, Long profileId);

    UserProfile getReferenceByUserIdAndProfileId(Long userId, Long profileId);
    List<UserProfile> getReferenceByUserId(Long userId);
}