package com.setthink.cerceveproje.service.serviceImpl;

import com.setthink.cerceveproje.entity.Musteri;
import com.setthink.cerceveproje.exception.notFound.MusteriNotFoundException;
import com.setthink.cerceveproje.repository.MusteriRepository;
import com.setthink.cerceveproje.service.MusteriService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MusteriServiceImpl implements MusteriService {

    MusteriRepository musteriRepository;

    @Override
    public Musteri getMusteri(Long id) {
        Optional<Musteri> optionalMusteri = musteriRepository.findById(id);
        return unwrapMusteri(optionalMusteri, id);
    }

    @Override
    public Musteri saveMusteri(Musteri musteri) {
        return musteriRepository.save(musteri);
    }

    @Override
    public void deleteMusteri(Long id) {
        Optional<Musteri> optionalMusteri = musteriRepository.findById(id);
        unwrapMusteri(optionalMusteri, id);
        musteriRepository.deleteById(id);
    }

    @Override
    public Musteri updateMusteri(Musteri musteri, Long id) {
        Optional<Musteri> optionalMusteri = musteriRepository.findById(id);
        Musteri updatedMusteri = unwrapMusteri(optionalMusteri, id);

        updatedMusteri.setMusteriAdi(musteri.getMusteriAdi());
        updatedMusteri.setMusteriNumara(musteri.getMusteriNumara());

        return musteriRepository.save(updatedMusteri);
    }

    @Override
    public List<Musteri> getAllMusteri() {
        return (List<Musteri>) musteriRepository.findAll();
    }

    static Musteri unwrapMusteri(Optional<Musteri> entity, Long id) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new MusteriNotFoundException(id);
        }

    }
}
