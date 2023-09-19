package com.setthink.cerceveproje.service.serviceImpl;

import com.setthink.cerceveproje.entity.Paspartu;
import com.setthink.cerceveproje.exception.PaspartuNotFoundException;
import com.setthink.cerceveproje.repository.PaspartuRepository;
import com.setthink.cerceveproje.service.PaspartuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PaspartuServiceImpl implements PaspartuService {

    PaspartuRepository paspartuRepository;

    @Override
    public Paspartu getPaspartu(Long id) {
        Optional<Paspartu> optionalPaspartu = paspartuRepository.findById(id);
        return unwrapPaspartu(optionalPaspartu, id);
    }

    @Override
    public Paspartu savePaspartu(Paspartu paspartu) {
        return paspartuRepository.save(paspartu);
    }

    @Override
    public void deletePaspartu(Long id) {
        Optional<Paspartu> optionalPaspartu = paspartuRepository.findById(id);
        unwrapPaspartu(optionalPaspartu, id);
        paspartuRepository.deleteById(id);
    }

    @Override
    public Paspartu updatePaspartu(Paspartu paspartu, Long id){
        Optional<Paspartu> optionalPaspartu = paspartuRepository.findById(id);
        Paspartu updatedPaspartu = unwrapPaspartu(optionalPaspartu, id);
        updatedPaspartu.setPaspartuKodu(paspartu.getPaspartuKodu());
        updatedPaspartu.setPaspartuFiyat(paspartu.getPaspartuFiyat());
        return paspartuRepository.save(updatedPaspartu);
    }


    @Override
    public List<Paspartu> getAllPaspartu() {
        return (List<Paspartu>) paspartuRepository.findAll();
    }




    static Paspartu unwrapPaspartu(Optional<Paspartu> entity, Long id) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new PaspartuNotFoundException(id);
        }
    }

    static Paspartu unwrapPaspartu(Optional<Paspartu> entity, String paspartuKodu) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new PaspartuNotFoundException(paspartuKodu);
        }
    }
}
