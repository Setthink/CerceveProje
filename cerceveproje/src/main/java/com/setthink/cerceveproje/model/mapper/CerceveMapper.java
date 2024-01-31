package com.setthink.cerceveproje.model.mapper;

import com.setthink.cerceveproje.CerceveprojeApplication;
import com.setthink.cerceveproje.entity.Cerceve;
import com.setthink.cerceveproje.model.request.CerceveRequest;
import com.setthink.cerceveproje.model.response.CerceveResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CerceveMapper {

    CerceveMapper INSTANCE = Mappers.getMapper(CerceveMapper.class);

    Cerceve toEntity(CerceveRequest request);

    CerceveResponse toResponse(Cerceve entity);

    List<CerceveResponse> toResponseList(List<Cerceve> entityList);
}
