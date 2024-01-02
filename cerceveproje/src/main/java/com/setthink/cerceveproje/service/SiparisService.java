package com.setthink.cerceveproje.service;

import com.setthink.cerceveproje.entity.Siparis;
import com.setthink.cerceveproje.web.model.SiparisRequest;

import java.util.List;

public interface SiparisService {
    Siparis getSiparis(Long id);

    Siparis saveSiparis(SiparisRequest SiparisRequest);

    List<Siparis> getAllSiparis();

    List<Siparis> getSiparisByMusteriId(Long musteriId);
    
    Siparis updateSiparis(SiparisRequest SiparisRequest, Long id);

    void deleteSiparis(Long id);

}
