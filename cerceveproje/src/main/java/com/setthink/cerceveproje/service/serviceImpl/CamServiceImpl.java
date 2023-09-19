package com.setthink.cerceveproje.service.serviceImpl;

import com.setthink.cerceveproje.entity.Cam;
import com.setthink.cerceveproje.exception.CamNotFoundException;
import com.setthink.cerceveproje.repository.CamRepository;
import com.setthink.cerceveproje.service.CamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CamServiceImpl implements CamService {

    CamRepository camRepository;

    @Override
    public Cam getCam(Long id){
        Optional<Cam> optionalCam = camRepository.findById(id);
        return unwrapCam(optionalCam,id);
    }

    @Override
    public Cam saveCam(Cam cam){
        return camRepository.save(cam);
    }


    @Override
    public void deleteCam(Long id){
        Optional<Cam> optionalCam = camRepository.findById(id);
        unwrapCam(optionalCam,id);
        camRepository.deleteById(id);
    }

    @Override
    public Cam updateCam(Cam cam,Long id){
        Optional<Cam> optionalCam = camRepository.findById(id);
        Cam updatedCam = unwrapCam(optionalCam,id);
        updatedCam.setCamKodu(cam.getCamKodu());
        updatedCam.setCamFiyat(cam.getCamFiyat());
        updatedCam.setCamModel(cam.getCamModel());

        return camRepository.save(updatedCam);

    }

    @Override
    public List<Cam> getAllCam(){
        return (List<Cam>) camRepository.findAll();
    }


    static Cam unwrapCam(Optional<Cam> entity, Long id){
        if (entity.isPresent()){
            return entity.get();
        }else{
            throw new CamNotFoundException(id);
        }
    }

    static Cam unwrapCam(Optional<Cam> entity, String camKodu){
        if (entity.isPresent()){
            return entity.get();
        }else{
            throw new CamNotFoundException(camKodu);
        }
    }
}
