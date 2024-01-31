package com.setthink.cerceveproje.service.serviceImpl;

import com.setthink.cerceveproje.entity.Ayna;
import com.setthink.cerceveproje.exception.notFound.AynaNotFoundException;
import com.setthink.cerceveproje.model.mapper.AynaMapper;
import com.setthink.cerceveproje.model.request.AynaRequest;
import com.setthink.cerceveproje.model.response.AynaResponse;
import com.setthink.cerceveproje.repository.AynaRepository;
import com.setthink.cerceveproje.service.AynaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class AynaServiceImpl implements AynaService {

    private final AynaRepository aynaRepository;
    private final AynaMapper aynaMapper; // Inject the mapper

    @Override
    public AynaResponse getAyna(Long id) {
        Optional<Ayna> optionalAyna = aynaRepository.findById(id);
        Ayna ayna = unwrapAyna(optionalAyna, id);
        return aynaMapper.toResponse(ayna);
    }

    @Override
    public AynaResponse saveAyna(AynaRequest aynaRequest) {
        Ayna aynaEntity = aynaMapper.toEntity(aynaRequest);
        aynaRepository.save(aynaEntity);
        return aynaMapper.toResponse(aynaEntity);
    }

    @Override
    public void deleteAyna(Long id) {
        Optional<Ayna> optionalAyna = aynaRepository.findById(id);
        Ayna ayna = unwrapAyna(optionalAyna, id);
        aynaRepository.deleteById(id);
    }

    @Override
    public AynaResponse updateAyna(AynaRequest aynaRequest, Long id) {
        Optional<Ayna> optionalAyna = aynaRepository.findById(id);
        Ayna ayna = unwrapAyna(optionalAyna, id);

        // Update the entity with data from the request
        aynaMapper.toEntity(aynaRequest);

        Ayna updatedAyna = aynaRepository.save(ayna);
        return aynaMapper.toResponse(updatedAyna);
    }

    @Override
    public List<AynaResponse> getAllAyna() {
        Iterable<Ayna> aynaIterable = aynaRepository.findAll();
        List<Ayna> aynaList = StreamSupport.stream(aynaIterable.spliterator(), false)
                .collect(Collectors.toList());
        return AynaMapper.INSTANCE.toResponseList(aynaList);
    }

    private Ayna unwrapAyna(Optional<Ayna> entity, Long id) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new AynaNotFoundException(id);
        }
    }
}
