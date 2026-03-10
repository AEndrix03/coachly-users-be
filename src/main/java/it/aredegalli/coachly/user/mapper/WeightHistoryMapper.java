package it.aredegalli.coachly.user.mapper;

import it.aredegalli.coachly.user.dto.WeightHistoryDto;
import it.aredegalli.coachly.user.model.WeightHistory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WeightHistoryMapper {

    WeightHistoryDto toDto(WeightHistory weightHistory);

    WeightHistory toEntity(WeightHistoryDto weightHistoryDto);

    List<WeightHistoryDto> toDtoList(List<WeightHistory> weightHistoryEntries);

    List<WeightHistory> toEntityList(List<WeightHistoryDto> weightHistoryDtos);
}
