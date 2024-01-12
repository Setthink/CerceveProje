package com.setthink.cerceveproje.service.serviceImpl;

import com.setthink.cerceveproje.entity.Cerceve;
import com.setthink.cerceveproje.exception.notFound.CerceveNotFoundException;
import com.setthink.cerceveproje.repository.CerceveRepository;
import com.setthink.cerceveproje.service.CerceveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CerceveServiceImpl implements CerceveService {

    CerceveRepository cerceveRepository;

    @Override
    public Cerceve getCerceve(Long id) {
        Optional<Cerceve> optionalCerceve = cerceveRepository.findById(id);
        return unwrapCerceve(optionalCerceve, id);
    }

    @Override
    public Cerceve saveCerceve(Cerceve cerceve) {
        return cerceveRepository.save(cerceve);
    }

    @Override
    public void deleteCerceve(Long id) {
        Optional<Cerceve> optionalCerceve = cerceveRepository.findById(id);
        unwrapCerceve(optionalCerceve, id);
        cerceveRepository.deleteById(id);
    }

    @Override
    public Cerceve updateCerceve(Cerceve cerceve, Long id) {
        Optional<Cerceve> optionalCerceve = cerceveRepository.findById(id);
        Cerceve updatedCerceve = unwrapCerceve(optionalCerceve, id);
        updatedCerceve.setCerceveKodu(cerceve.getCerceveKodu());
        updatedCerceve.setCerceveBoyutu(cerceve.getCerceveBoyutu());
        updatedCerceve.setCerceveGenislik(cerceve.getCerceveGenislik());
        updatedCerceve.setCerceveFiyat(cerceve.getCerceveFiyat());
        return cerceveRepository.save(updatedCerceve);
    }

    @Override
    public List<Cerceve> getAllCerceve() {
        return (List<Cerceve>) cerceveRepository.findAll();
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
