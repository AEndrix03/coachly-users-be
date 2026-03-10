package it.aredegalli.coachly.user.repository;

import com.coachly.userprofile.model.enums.AuthProvider;
import it.aredegalli.coachly.user.model.ExternalIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExternalIdentityRepository extends JpaRepository<ExternalIdentity, UUID> {

    Optional<ExternalIdentity> findByProviderAndProviderUserId(AuthProvider provider, String providerUserId);

    List<ExternalIdentity> findAllByUserId(UUID userId);
}
