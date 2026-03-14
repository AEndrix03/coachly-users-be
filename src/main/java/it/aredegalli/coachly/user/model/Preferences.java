package it.aredegalli.coachly.user.model;

import it.aredegalli.coachly.user.enums.HeightUnit;
import it.aredegalli.coachly.user.enums.Theme;
import it.aredegalli.coachly.user.enums.WeightUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * User app preferences.
 *
 * <p>One row per user. Always upsert, never insert a second row.
 * Created automatically at provisioning time with sensible defaults.
 *
 * <p>{@link #notificationSettings} is stored as JSONB for flexibility:
 * new notification channels can be added without schema migrations.
 *
 * <p>{@link #preferredWeightUnit} and {@link #preferredHeightUnit} are
 * deliberately denormalized here so that other services (workout-be,
 * ai-service) can read the user's unit preference in a single call
 * without joining to the profile table.
 *
 * <p>The JSONB column uses Hibernate native JSON support via
 * {@link JdbcTypeCode} and {@link SqlTypes#JSON}.
 */
@Entity
@Table(schema = "user_profile", name = "preferences")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Preferences {

    /**
     * Same UUID as {@link Profile#userId}. This is both the PK and the
     * logical foreign key to the profile table.
     */
    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID userId;

    // Locale & display

    /**
     * BCP-47 language tag, e.g. "it", "en", "es-MX".
     * Propagated by the gateway as the X-Locale header to all downstream services.
     */
    @Column(name = "locale", nullable = false, length = 10)
    private String locale;

    /**
     * IANA timezone identifier, e.g. "Europe/Rome", "America/New_York".
     */
    @Column(name = "timezone", nullable = false, length = 50)
    private String timezone;

    @Enumerated(EnumType.STRING)
    @Column(name = "theme", nullable = false, length = 10)
    private Theme theme;

    // Unit preferences

    /**
     * Preferred unit for weight display across the entire app.
     * Denormalized here for single-query access by other services.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "preferred_weight_unit", nullable = false, length = 10)
    private WeightUnit preferredWeightUnit;

    /**
     * Preferred unit for height display across the entire app.
     * Denormalized here for single-query access by other services.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "preferred_height_unit", nullable = false, length = 10)
    private HeightUnit preferredHeightUnit;

    // Notifications

    /**
     * Flexible notification configuration stored as JSONB.
     *
     * <p>Expected structure:
     * <pre>{@code
     * {
     *   "push_enabled": true,
     *   "email_enabled": false,
     *   "channels": {
     *     "workout_reminder": { "enabled": true, "time": "08:00" },
     *     "motivational":     { "enabled": true },
     *     "streak_alert":     { "enabled": true },
     *     "coach_message":    { "enabled": true }
     *   }
     * }
     * }</pre>
     *
     * <p>New channels can be added to the JSON without schema migrations.
     * Deserialized at the service layer.
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "notification_settings", nullable = false, columnDefinition = "jsonb")
    private String notificationSettings;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    // Lifecycle hooks

    @PrePersist
    protected void onCreate() {
        this.updatedAt = OffsetDateTime.now();
        applyDefaults();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

    private void applyDefaults() {
        if (this.locale == null)               this.locale = "en";
        if (this.timezone == null)             this.timezone = "UTC";
        if (this.theme == null)                this.theme = Theme.SYSTEM;
        if (this.preferredWeightUnit == null)  this.preferredWeightUnit = WeightUnit.KG;
        if (this.preferredHeightUnit == null)  this.preferredHeightUnit = HeightUnit.CM;
        if (this.notificationSettings == null) {
            this.notificationSettings = """
                    {
                      "push_enabled": true,
                      "email_enabled": false,
                      "channels": {
                        "workout_reminder": {"enabled": true, "time": "08:00"},
                        "motivational":     {"enabled": true},
                        "streak_alert":     {"enabled": true},
                        "coach_message":    {"enabled": true}
                      }
                    }
                    """;
        }
    }
}
