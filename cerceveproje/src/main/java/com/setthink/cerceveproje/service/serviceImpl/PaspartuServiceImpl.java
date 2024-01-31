package com.setthink.cerceveproje.service.serviceImpl;

import com.setthink.cerceveproje.entity.Paspartu;
import com.setthink.cerceveproje.exception.notFound.PaspartuNotFoundException;
import com.setthink.cerceveproje.model.mapper.PaspartuMapper;
import com.setthink.cerceveproje.model.request.PaspartuRequest;
import com.setthink.cerceveproje.model.response.PaspartuResponse;
import com.setthink.cerceveproje.repository.PaspartuRepository;
import com.setthink.cerceveproje.service.PaspartuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class PaspartuServiceImpl implements PaspartuService {

    private final PaspartuRepository paspartuRepository;
    private final PaspartuMapper paspartuMapper;

    @Override
    public PaspartuResponse getPaspartu(Long id) {
        Optional<Paspartu> optionalPaspartu = paspartuRepository.findById(id);
        Paspartu paspartu = unwrapPaspartu(optionalPaspartu, id);
        return paspartuMapper.toResponse(paspartu);
    }

    @Override
    public PaspartuResponse savePaspartu(PaspartuRequest paspartuRequest) {
        Paspartu paspartuEntity = paspartuMapper.toEntity(paspartuRequest);
        paspartuRepository.save(paspartuEntity);
        return paspartuMapper.toResponse(paspartuEntity);
    }

    @Override
    public void deletePaspartu(Long id) {
        Optional<Paspartu> optionalPaspartu = paspartuRepository.findById(id);
        unwrapPaspartu(optionalPaspartu, id);
        paspartuRepository.deleteById(id);
    }

    @Override
    public PaspartuResponse updatePaspartu(PaspartuRequest paspartuRequest, Long id) {
        Optional<Paspartu> optionalPaspartu = paspartuRepository.findById(id);
        Paspartu paspartu = unwrapPaspartu(optionalPaspartu, id);

        paspartuMapper.toEntity(paspartuRequest);

        Paspartu updatedPaspartu = paspartuRepository.save(paspartu);
        return paspartuMapper.toResponse(updatedPaspartu);
    }


    @Override
    public List<PaspartuResponse> getAllPaspartu() {
        Iterable<Paspartu> paspartuIterable = paspartuRepository.findAll();
        List<Paspartu> paspartuList = StreamSupport.stream(paspartuIterable.spliterator(), false)
                .collect(Collectors.toList());
        return paspartuMapper.toResponseList(paspartuList);
    }


    static Paspartu unwrapPaspartu(Optional<Paspartu> entity, Long id) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new PaspartuNotFoundException(id);
        }
    }

}
