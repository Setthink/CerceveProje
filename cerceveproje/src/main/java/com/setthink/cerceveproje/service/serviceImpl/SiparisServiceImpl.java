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
        List<Ayna> ayna = aynaRepository.findByAynaKoduIn(siparis.getAynaKodu());
        if(cerceve.size() != siparis.getCerceveKodu().size())
            throw new CerceveNotFoundException(siparis.getCerceveKodu().toString());
        else if(cam.size() != siparis.getCamKodu().size())
            throw new CamNotFoundException(siparis.getCamKodu().toString());
        else if(paspartu.size() != siparis.getPaspartuKodu().size())
            throw new PaspartuNotFoundException(siparis.getPaspartuKodu().toString());
        else if(ayna.size() != siparis.getAynaKodu().size())
            throw new AynaNotFoundException(siparis.getAynaKodu().toString());
        else if(musteriRepository.findById(siparis.getMusteriId()).isEmpty())
            throw new MusteriNotFoundException(siparis.getMusteriId());
        else {
            Siparis siparis1 = new Siparis();
            try{
                siparis1.setBoy(siparis.getBoy());
                siparis1.setEn(siparis.getEn());
                calculateCerceveUsage(siparis);
                siparis1.setCerceveler(cerceve);
                siparis1.setCamlar(cam);
                siparis1.setPaspartular(paspartu);
                siparis1.setAynalar(ayna);
                siparis1.setMusteri(musteriRepository.findById(siparis.getMusteriId()).get());
                siparis1.setSiparisNot(siparis.getSiparisNot());
                siparis1.setSiparisTarih(siparis.getSiparisTarih());
                siparis1.setSiparisFiyat(siparis.getSiparisFiyat());
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
        updatedSiparis.setAynalar(aynaRepository.findByAynaKoduIn(siparis.getAynaKodu()));
        updatedSiparis.setMusteri(musteriRepository.findById(siparis.getMusteriId()).get());
        return siparisRepository.save(updatedSiparis);
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

    public void calculateCerceveUsage(SiparisRequest siparis){
        List<Cerceve> cerceve = cerceveRepository.findByCerceveKoduIn(siparis.getCerceveKodu());
        Cerceve firstCerceve = cerceve.get(0);
        float En = siparis.getEn();
        float Boy = siparis.getBoy();
        for(Cerceve foe : cerceve){
            float cerceveUsage;
            if(foe == firstCerceve) {
                cerceveUsage = (En + foe.getCerceveGenislik()) * 2 + (Boy + foe.getCerceveGenislik()) * 2;
                if (cerceveUsage > foe.getCerceveBoyutu())
                    throw new CerceveNotEnoughException(siparis.getCerceveKodu().toString());
                else{
                    foe.setCerceveBoyutu(foe.getCerceveBoyutu() - cerceveUsage);
                }
            } else if (foe == cerceve.get(1)){
                cerceveUsage = ((((En + foe.getCerceveGenislik()) * 2) + firstCerceve.getCerceveGenislik()) * 2) +
                        ((((Boy + foe.getCerceveGenislik()) * 2) + firstCerceve.getCerceveGenislik()) * 2);
                if (cerceveUsage > foe.getCerceveBoyutu())
                    throw new CerceveNotEnoughException(siparis.getCerceveKodu().toString());
                else{
                    foe.setCerceveBoyutu(foe.getCerceveBoyutu() - cerceveUsage);
                }
            }
        }
    }
}
