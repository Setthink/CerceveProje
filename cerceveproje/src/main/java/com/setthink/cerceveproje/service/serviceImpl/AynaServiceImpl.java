package com.setthink.cerceveproje.service.serviceImpl;

import com.setthink.cerceveproje.entity.Ayna;
import com.setthink.cerceveproje.exception.AynaNotFoundException;
import com.setthink.cerceveproje.repository.AynaRepository;
import com.setthink.cerceveproje.service.AynaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AynaServiceImpl implements AynaService {
    AynaRepository aynaRepository;

    @Override
    public Ayna getAyna(Long id) {
        Optional<Ayna> optionalAyna = aynaRepository.findById(id);
        return unwrapAyna(optionalAyna, id);
    }

    @Override
    public Ayna saveAyna(Ayna ayna) {
        return aynaRepository.save(ayna);
    }

    @Override
    public void deleteAyna(Long id) {
        Optional<Ayna> optionalAyna = aynaRepository.findById(id);
        unwrapAyna(optionalAyna, id);
        aynaRepository.deleteById(id);
    }

    @Override
    public Ayna updateAyna(Ayna ayna, Long id) {
        Optional<Ayna> optionalAyna = aynaRepository.findById(id);
        Ayna updatedAyna = unwrapAyna(optionalAyna, id);
        updatedAyna.setAynaKodu(ayna.getAynaKodu());
        updatedAyna.setAynaFiyat(ayna.getAynaFiyat());
        return aynaRepository.save(updatedAyna);
    }

    @Override
    public List<Ayna> getAllAyna() {
        return (List<Ayna>) aynaRepository.findAll();
    }


    static Ayna unwrapAyna(Optional<Ayna> entity, Long id) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new AynaNotFoundException(id);
        }
    }

    static Ayna unwrapAyna(Optional<Ayna> entity, String aynaKodu) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new AynaNotFoundException(aynaKodu);
        }
    }
}
