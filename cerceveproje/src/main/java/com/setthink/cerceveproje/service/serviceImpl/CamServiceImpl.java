package com.setthink.cerceveproje.service.serviceImpl;

import com.setthink.cerceveproje.entity.Cam;
import com.setthink.cerceveproje.exception.notFound.CamNotFoundException;
import com.setthink.cerceveproje.model.mapper.CamMapper;
import com.setthink.cerceveproje.model.request.CamRequest;
import com.setthink.cerceveproje.model.response.CamResponse;
import com.setthink.cerceveproje.repository.CamRepository;
import com.setthink.cerceveproje.service.CamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class CamServiceImpl implements CamService {

    private final CamRepository camRepository;
    private final CamMapper camMapper;

    @Override
    public CamResponse getCam(Long id) {
        Optional<Cam> optionalCam = camRepository.findById(id);
        Cam cam = unwrapCam(optionalCam, id);
        return camMapper.toResponse(cam);
    }

    @Override
    public CamResponse saveCam(CamRequest camRequest) {
        Cam camEntity = camMapper.toEntity(camRequest);
        camRepository.save(camEntity);
        return camMapper.toResponse(camEntity);
    }

    @Override
    public void deleteCam(Long id) {
        Optional<Cam> optionalCam = camRepository.findById(id);
        Cam cam = unwrapCam(optionalCam, id);
        camRepository.deleteById(id);
    }

    @Override
    public CamResponse updateCam(CamRequest camRequest, Long id) {
        Optional<Cam> optionalCam = camRepository.findById(id);
        Cam cam = unwrapCam(optionalCam, id);
        camMapper.toEntity(camRequest);
        Cam updatedCam = camRepository.save(cam);
        return camMapper.toResponse(updatedCam);
    }

    @Override
    public List<CamResponse> getAllCam() {
        Iterable<Cam> camIterable = camRepository.findAll();
        List<Cam> camList = StreamSupport.stream(camIterable.spliterator(), false)
                .collect(Collectors.toList());
        return camMapper.toResponseList(camList);
    }

    static Cam unwrapCam(Optional<Cam> entity, Long id) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new CamNotFoundException(id);
        }
    }

    static Cam unwrapCam(Optional<Cam> entity, String camKodu) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new CamNotFoundException(camKodu);
        }
    }

}
