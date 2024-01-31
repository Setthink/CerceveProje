package com.setthink.cerceveproje.service.serviceImpl;

import com.setthink.cerceveproje.entity.Cerceve;
import com.setthink.cerceveproje.exception.notFound.CerceveNotFoundException;
import com.setthink.cerceveproje.model.mapper.CerceveMapper;
import com.setthink.cerceveproje.model.request.CerceveRequest;
import com.setthink.cerceveproje.model.response.CerceveResponse;
import com.setthink.cerceveproje.repository.CerceveRepository;
import com.setthink.cerceveproje.service.CerceveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class CerceveServiceImpl implements CerceveService {

    private final CerceveRepository cerceveRepository;
    private final CerceveMapper cerceveMapper;


    @Override
    public CerceveResponse getCerceve(Long id) {
        Optional<Cerceve> optionalCerceve = cerceveRepository.findById(id);
        Cerceve cerceve = unwrapCerceve(optionalCerceve, id);
        return cerceveMapper.toResponse(cerceve);
    }

    @Override
    public CerceveResponse saveCerceve(CerceveRequest cerceve) {
        Cerceve cerceveEntity = cerceveMapper.toEntity(cerceve);
        cerceveRepository.save(cerceveEntity);
        return cerceveMapper.toResponse(cerceveEntity);
    }

    @Override
    public void deleteCerceve(Long id) {
        Optional<Cerceve> optionalCerceve = cerceveRepository.findById(id);
        Cerceve cerceve = unwrapCerceve(optionalCerceve, id);
        cerceveRepository.deleteById(id);
    }

    @Override
    public CerceveResponse updateCerceve(CerceveRequest cerceveRequest, Long id) {
        Optional<Cerceve> optionalCerceve = cerceveRepository.findById(id);
        Cerceve cerceve = unwrapCerceve(optionalCerceve, id);

        // Update the entity with data from the request
        cerceveMapper.toEntity(cerceveRequest);

        Cerceve updatedCerceve = cerceveRepository.save(cerceve);
        return cerceveMapper.toResponse(updatedCerceve);
    }

    @Override
    public List<CerceveResponse> getAllCerceve() {
        Iterable<Cerceve> cerceveIterable = cerceveRepository.findAll();
        List<Cerceve> cerceveList = StreamSupport.stream(cerceveIterable.spliterator(), false)
                .collect(Collectors.toList());
        return cerceveMapper.toResponseList(cerceveList);
    }

    static Cerceve unwrapCerceve(Optional<Cerceve> entity, Long id) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new CerceveNotFoundException(id);
        }
    }

    static Cerceve unwrapCerceve(Optional<Cerceve> entity, String cerceveKodu) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new CerceveNotFoundException(cerceveKodu);
        }
    }

}
