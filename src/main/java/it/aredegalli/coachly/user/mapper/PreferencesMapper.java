package it.aredegalli.coachly.user.mapper;

import it.aredegalli.coachly.user.dto.PreferencesDto;
import it.aredegalli.coachly.user.model.Preferences;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PreferencesMapper {

    PreferencesDto toDto(Preferences preferences);

    Preferences toEntity(PreferencesDto preferencesDto);

    List<PreferencesDto> toDtoList(List<Preferences> preferencesList);

    List<Preferences> toEntityList(List<PreferencesDto> preferencesDtos);
}
