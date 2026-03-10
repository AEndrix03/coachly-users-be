package it.aredegalli.coachly.user.mapper;

import it.aredegalli.coachly.user.dto.ExternalIdentityDto;
import it.aredegalli.coachly.user.model.ExternalIdentity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExternalIdentityMapper {

    ExternalIdentityDto toDto(ExternalIdentity externalIdentity);

    ExternalIdentity toEntity(ExternalIdentityDto externalIdentityDto);

    List<ExternalIdentityDto> toDtoList(List<ExternalIdentity> externalIdentities);

    List<ExternalIdentity> toEntityList(List<ExternalIdentityDto> externalIdentityDtos);
}
