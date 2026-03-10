package it.aredegalli.coachly.user.dto;

import com.coachly.userprofile.model.enums.BiologicalSex;
import com.coachly.userprofile.model.enums.FitnessLevel;
import com.coachly.userprofile.model.enums.HeightUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    private UUID userId;
    private String displayName;
    private String avatarUrl;
    private LocalDate dateOfBirth;
    private BiologicalSex biologicalSex;
    private BigDecimal height;
    private HeightUnit heightUnit;
    private FitnessLevel fitnessLevel;
    private Short weeklyTarget;
}
