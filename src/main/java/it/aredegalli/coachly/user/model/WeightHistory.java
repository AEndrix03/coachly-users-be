package it.aredegalli.coachly.user.model;

import com.coachly.userprofile.model.enums.WeightUnit;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Append-only weight history entry.
 *
 * <p><strong>Rules:</strong>
 * <ul>
 *   <li>Never UPDATE rows in this table — only INSERT.</li>
 *   <li>Current weight = latest row per userId ordered by recordedAt DESC.</li>
 *   <li>All write operations must go through the service layer, never direct
 *       repository saves over existing rows.</li>
 * </ul>
 *
 * <p>The append-only nature provides a free progress history for charts
 * without any additional overhead.
 */
@Entity
@Table(schema = "user_profile", name = "weight_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeightHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    /**
     * Internal user ID. References {@link Profile#userId}.
     */
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID userId;

    /**
     * Body weight value, stored in the unit specified by {@link #unit}.
     */
    @Column(name = "weight", nullable = false, precision = 5, scale = 1)
    private BigDecimal weight;

    /**
     * Unit of the stored weight value.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "unit", nullable = false, length = 10)
    private WeightUnit unit;

    /**
     * When the user logged this measurement. May differ from the DB insert
     * time if the user is logging a past measurement.
     */
    @Column(name = "recorded_at", nullable = false, updatable = false)
    private OffsetDateTime recordedAt;

    /**
     * Optional free-text context provided by the user,
     * e.g. "morning fasted", "post-workout".
     */
    @Column(name = "note", length = 255)
    private String note;

    // ── Lifecycle hooks ──────────────────────────────────────

    @PrePersist
    protected void onCreate() {
        if (this.recordedAt == null) {
            this.recordedAt = OffsetDateTime.now();
        }
    }
}