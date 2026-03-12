package it.aredegalli.coachly.user.service;

import it.aredegalli.coachly.user.model.ExternalIdentity;
import it.aredegalli.coachly.user.repository.ExternalIdentityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InternalUserServiceTest {

    @Mock
    private ExternalIdentityRepository externalIdentityRepository;

    @InjectMocks
    private InternalUserService internalUserService;

    @Test
    void resolveUserIdReturnsMappedInternalUserId() {
        String externalId = "jwt-sub";
        UUID userId = UUID.randomUUID();

        when(externalIdentityRepository.findByProviderUserId(externalId))
                .thenReturn(Optional.of(ExternalIdentity.builder().userId(userId).build()));

        UUID result = internalUserService.resolveUserId(externalId);

        assertEquals(userId, result);

        ArgumentCaptor<ExternalIdentity> savedIdentity = ArgumentCaptor.forClass(ExternalIdentity.class);
        verify(externalIdentityRepository).save(savedIdentity.capture());
        assertNotNull(savedIdentity.getValue().getLastLoginAt());
    }

    @Test
    void resolveUserIdThrowsWhenIdentityIsMissing() {
        String externalId = "missing";

        when(externalIdentityRepository.findByProviderUserId(externalId))
                .thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> internalUserService.resolveUserId(externalId)
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
