package com.setthink.cerceveproje.model.mapper;

import com.setthink.cerceveproje.entity.Paspartu;
import com.setthink.cerceveproje.model.request.PaspartuRequest;
import com.setthink.cerceveproje.model.response.PaspartuResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaspartuMapper {

    PaspartuMapper INSTANCE = Mappers.getMapper(PaspartuMapper.class);

    Paspartu toEntity(PaspartuRequest request);

    PaspartuResponse toResponse(Paspartu entity);

    List<PaspartuResponse> toResponseList(List<Paspartu> entityList);

}
