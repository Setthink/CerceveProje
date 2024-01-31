package com.setthink.cerceveproje.model.mapper;

import com.setthink.cerceveproje.entity.Cam;
import com.setthink.cerceveproje.model.request.CamRequest;
import com.setthink.cerceveproje.model.response.CamResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CamMapper {

    CamMapper INSTANCE = Mappers.getMapper(CamMapper.class);

    Cam toEntity(CamRequest request);

    CamResponse toResponse(Cam entity);

    List<CamResponse> toResponseList(List<Cam> entityList);
}
