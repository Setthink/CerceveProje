package com.setthink.cerceveproje.model.mapper;

import com.setthink.cerceveproje.entity.Ayna;
import com.setthink.cerceveproje.model.request.AynaRequest;
import com.setthink.cerceveproje.model.response.AynaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AynaMapper {
    AynaMapper INSTANCE = Mappers.getMapper(AynaMapper.class);


    Ayna toEntity(AynaRequest request);


    AynaResponse toResponse(Ayna entity);

    List<AynaResponse> toResponseList(List<Ayna> entityList);

}
