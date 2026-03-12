package it.aredegalli.coachly.user.service;

import it.aredegalli.coachly.user.model.ExternalIdentity;
import it.aredegalli.coachly.user.repository.ExternalIdentityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class InternalUserService {

    private final ExternalIdentityRepository externalIdentityRepository;

    public InternalUserService(ExternalIdentityRepository externalIdentityRepository) {
        this.externalIdentityRepository = externalIdentityRepository;
    }

    @Transactional
    public UUID resolveUserId(String externalId) {
        ExternalIdentity identity = externalIdentityRepository.findByProviderUserId(externalId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No internal user found for externalId " + externalId
                ));

        identity.setLastLoginAt(OffsetDateTime.now());
        externalIdentityRepository.save(identity);
        return identity.getUserId();
    }
}
