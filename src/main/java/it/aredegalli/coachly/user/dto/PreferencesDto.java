package it.aredegalli.coachly.user.dto;

import com.coachly.userprofile.model.enums.HeightUnit;
import com.coachly.userprofile.model.enums.Theme;
import com.coachly.userprofile.model.enums.WeightUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreferencesDto {

    private UUID userId;
    private String locale;
    private String timezone;
    private Theme theme;
    private WeightUnit preferredWeightUnit;
    private HeightUnit preferredHeightUnit;
    private String notificationSettings;
}
