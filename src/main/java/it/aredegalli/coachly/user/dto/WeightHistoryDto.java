package it.aredegalli.coachly.user.dto;

import com.coachly.userprofile.model.enums.WeightUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeightHistoryDto {

    private UUID id;
    private UUID userId;
    private BigDecimal weight;
    private WeightUnit unit;
    private OffsetDateTime recordedAt;
    private String note;
}
