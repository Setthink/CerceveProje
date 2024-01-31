package com.setthink.cerceveproje.service.serviceImpl;

import com.setthink.cerceveproje.entity.Musteri;
import com.setthink.cerceveproje.exception.notFound.MusteriNotFoundException;
import com.setthink.cerceveproje.model.mapper.MusteriMapper;
import com.setthink.cerceveproje.model.request.MusteriRequest;
import com.setthink.cerceveproje.model.response.MusteriResponse;
import com.setthink.cerceveproje.repository.MusteriRepository;
import com.setthink.cerceveproje.service.MusteriService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class MusteriServiceImpl implements MusteriService {

    private final MusteriRepository musteriRepository;
    private final MusteriMapper musteriMapper;

    @Override
    public MusteriResponse getMusteri(Long id) {
        Optional<Musteri> optionalMusteri = musteriRepository.findById(id);
        Musteri musteri = unwrapMusteri(optionalMusteri, id);
        return musteriMapper.toResponse(musteri);
    }

    @Override
    public MusteriResponse saveMusteri(MusteriRequest musteriRequest) {
        Musteri musteriEntity = musteriMapper.toEntity(musteriRequest);
        musteriRepository.save(musteriEntity);
        return musteriMapper.toResponse(musteriEntity);
    }

    @Override
    public void deleteMusteri(Long id) {
        Optional<Musteri> optionalMusteri = musteriRepository.findById(id);
        Musteri musteri = unwrapMusteri(optionalMusteri, id);
        musteriRepository.deleteById(id);
    }

    @Override
    public MusteriResponse updateMusteri(MusteriRequest musteriRequest, Long id) {
        Optional<Musteri> optionalMusteri = musteriRepository.findById(id);
        Musteri musteri = unwrapMusteri(optionalMusteri, id);

        musteriMapper.toEntity(musteriRequest);

        Musteri updatedMusteri = musteriRepository.save(musteri);
        return musteriMapper.toResponse(updatedMusteri);
    }

    @Override
    public List<MusteriResponse> getAllMusteri() {
        Iterable<Musteri> musteriIterable = musteriRepository.findAll();
        List<Musteri> musteriList = StreamSupport.stream(musteriIterable.spliterator(), false)
                .collect(Collectors.toList());
        return musteriMapper.toResponseList(musteriList);
    }

    static Musteri unwrapMusteri(Optional<Musteri> entity, Long id) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new MusteriNotFoundException(id);
        }

    }
}
