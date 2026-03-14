package it.aredegalli.coachly.user.model;

import it.aredegalli.coachly.user.enums.AuthProvider;
import it.aredegalli.coachly.user.persistence.AuthProviderConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Identity federation entity.
 *
 * <p>Maps an external provider identity (provider + sub claim) to the
 * stable internal {@link Profile#userId}. One user can have multiple rows,
 * one per linked provider.
 *
 * <p>This is the <strong>only</strong> entity that stores external provider IDs.
 * The gateway resolves (provider, providerUserId) → userId on every authenticated
 * request — keep {@code idx_ext_identity_lookup} healthy.
 *
 * <p>Example: the same user may have rows for "google" and "keycloak" both
 * pointing to the same userId, enabling seamless account linking and provider
 * migration without data loss.
 */
@Entity
@Table(
        schema = "user_profile",
        name = "external_identity",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_external_identity_provider",
                columnNames = {"provider", "provider_user_id"}
        )
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExternalIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    /**
     * Stable internal user ID. References {@link Profile#userId}.
     * Declared as a plain UUID column (no @ManyToOne) to avoid
     * cross-schema lazy loading issues.
     */
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID userId;

    /**
     * The authentication provider that issued the identity.
     */
    @Convert(converter = AuthProviderConverter.class)
    @Column(name = "provider", nullable = false, length = 20, updatable = false)
    private AuthProvider provider;

    /**
     * The "sub" claim from the provider JWT. Stable and unique per provider.
     * Never use this as a user identifier outside of this service.
     */
    @Column(name = "provider_user_id", nullable = false, length = 255, updatable = false)
    private String providerUserId;

    /**
     * Email reported by the provider. Stored for reference only —
     * never used as an identifier or for deduplication.
     */
    @Column(name = "provider_email", length = 255)
    private String providerEmail;

    /**
     * Timestamp when this provider was linked to the account.
     */
    @Column(name = "linked_at", nullable = false, updatable = false)
    private OffsetDateTime linkedAt;

    /**
     * Updated on every successful identity resolution by the gateway.
     * Useful for activity auditing and detecting inactive providers.
     */
    @Column(name = "last_login_at")
    private OffsetDateTime lastLoginAt;

    // ── Lifecycle hooks ──────────────────────────────────────

    @PrePersist
    protected void onCreate() {
        this.linkedAt = OffsetDateTime.now();
    }
}
