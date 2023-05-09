package com.mbo.mapper;

import com.mbo.dto.DemandDto;
import com.mbo.model.Demand;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
//@DecoratedWith(DemandMapper.class)
public interface DemandMapper {
    DemandDto demandToDemandDto(Demand demand);
    Demand demandDtoToDemand(DemandDto demandDto);
}
