package it.aredegalli.coachly.user.dto;

import com.coachly.userprofile.model.enums.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalIdentityDto {

    private UUID id;
    private UUID userId;
    private AuthProvider provider;
    private String providerUserId;
    private String providerEmail;
    private OffsetDateTime linkedAt;
    private OffsetDateTime lastLoginAt;
}
