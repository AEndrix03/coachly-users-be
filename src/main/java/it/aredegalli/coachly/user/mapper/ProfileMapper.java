package it.aredegalli.coachly.user.mapper;

import it.aredegalli.coachly.user.dto.ProfileDto;
import it.aredegalli.coachly.user.model.Profile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileDto toDto(Profile profile);

    Profile toEntity(ProfileDto profileDto);

    List<ProfileDto> toDtoList(List<Profile> profiles);

    List<Profile> toEntityList(List<ProfileDto> profileDtos);
}
