package com.setthink.cerceveproje.model.mapper;
import com.setthink.cerceveproje.entity.Musteri;
import com.setthink.cerceveproje.model.request.MusteriRequest;
import com.setthink.cerceveproje.model.response.MusteriResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MusteriMapper {

    MusteriMapper INSTANCE = Mappers.getMapper(MusteriMapper.class);

    Musteri toEntity(MusteriRequest request);

    MusteriResponse toResponse(Musteri entity);

    List<MusteriResponse> toResponseList(List<Musteri> entityList);
}
