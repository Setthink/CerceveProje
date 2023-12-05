package com.setthink.cerceveproje.service.serviceImpl;

import com.setthink.cerceveproje.entity.*;
import com.setthink.cerceveproje.exception.*;
import com.setthink.cerceveproje.repository.*;
import com.setthink.cerceveproje.service.SiparisService;
import com.setthink.cerceveproje.web.model.SiparisRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SiparisServiceImpl implements SiparisService {

    SiparisRepository siparisRepository;
    CerceveRepository cerceveRepository;
    CamRepository camRepository;
    PaspartuRepository paspartuRepository;
    AynaRepository aynaRepository;
    MusteriRepository musteriRepository;

    @Override
    public Siparis getSiparis(Long id) {
        Optional<Siparis> siparis = siparisRepository.findById(id);
        return unwrapSiparis(siparis, id);
    }

    @Override
    public Siparis saveSiparis(SiparisRequest siparis) {

        List<Cerceve> cerceve = cerceveRepository.findByCerceveKoduIn(siparis.getCerceveKodu());
        List<Cam> cam = camRepository.findByCamKoduIn(siparis.getCamKodu());
        List<Paspartu> paspartu = paspartuRepository.findByPaspartuKoduIn(siparis.getPaspartuKodu());
        if(cerceve.size() != siparis.getCerceveKodu().size())
            throw new CerceveNotFoundException(siparis.getCerceveKodu().toString());
        else if(cam.size() != siparis.getCamKodu().size())
            throw new CamNotFoundException(siparis.getCamKodu().toString());
        else if(paspartu.size() != siparis.getPaspartuKodu().size())
            throw new PaspartuNotFoundException(siparis.getPaspartuKodu().toString());
        else if(aynaRepository.getAynaByAynaKodu(siparis.getAynaKodu()).isEmpty())
            throw new AynaNotFoundException(siparis.getAynaKodu());
        else if(musteriRepository.findById(siparis.getMusteriId()).isEmpty())
            throw new MusteriNotFoundException(siparis.getMusteriId());
        else {
            Siparis siparis1 = new Siparis();
            try{
                siparis1.setBoy(siparis.getBoy());
                siparis1.setEn(siparis.getEn());
                siparis1.setCerceveler(cerceve);
                siparis1.setCamlar(cam);
                siparis1.setPaspartular(paspartu);
                siparis1.setAyna(aynaRepository.getAynaByAynaKodu(siparis.getAynaKodu()).get());
                siparis1.setMusteri(musteriRepository.findById(siparis.getMusteriId()).get());
                siparis1.setSiparisNot(siparis.getSiparisNot());
                siparis1.setSiparisTarih(siparis.getSiparisTarih());
                siparis1.setSiparisFiyat(calculateSiparisFiyat(siparis1));
                return siparisRepository.save(siparis1);

            }catch (Exception e){
                throw new CerceveNotEnoughException(siparis.getCerceveKodu().toString());
            }
        }
    }

    @Override
    public List<Siparis> getAllSiparis() {
        return (List<Siparis>) siparisRepository.findAll();
    }

    @Override
    public Siparis updateSiparis(SiparisRequest siparis, Long id) {
        Optional<Siparis> optionalSiparis = siparisRepository.findById(id);
        Siparis updatedSiparis = unwrapSiparis(optionalSiparis, id);

        updatedSiparis.setEn(siparis.getEn());
        updatedSiparis.setBoy(siparis.getBoy());
        updatedSiparis.setSiparisNot(siparis.getSiparisNot());
        updatedSiparis.setSiparisTarih(siparis.getSiparisTarih());
        updatedSiparis.setCerceveler(cerceveRepository.findByCerceveKoduIn(siparis.getCerceveKodu()));
        updatedSiparis.setCamlar(camRepository.findByCamKoduIn(siparis.getCamKodu()));
        updatedSiparis.setPaspartular(paspartuRepository.findByPaspartuKoduIn(siparis.getPaspartuKodu()));
        updatedSiparis.setAyna(aynaRepository.getAynaByAynaKodu(siparis.getAynaKodu()).get());
        updatedSiparis.setMusteri(musteriRepository.findById(siparis.getMusteriId()).get());
        return siparisRepository.save(updatedSiparis);
    }

    public float calculateSiparisFiyat(Siparis siparis) {
        return siparis.getEn() * siparis.getBoy() * 10;
    }

    @Override
    public List<Siparis> getSiparisByMusteriId(Long musteriId) {
        return siparisRepository.findByMusteriId(musteriId);
    }

    @Override
    public void deleteSiparis(Long id) {
        Optional<Siparis> siparis = siparisRepository.findById(id);
        Siparis deletedSiparis = unwrapSiparis(siparis, id);
        siparisRepository.delete(deletedSiparis);
    }

    static Siparis unwrapSiparis(Optional<Siparis> entity, Long id) {
        if(entity.isPresent()) return entity.get();
        else throw new SiparisNotFoundException(id);
    }
}
