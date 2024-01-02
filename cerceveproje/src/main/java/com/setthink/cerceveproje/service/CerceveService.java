package com.setthink.cerceveproje.service;

import com.setthink.cerceveproje.entity.Cerceve;

import java.util.List;

public interface CerceveService {
    Cerceve getCerceve(Long id);

    Cerceve saveCerceve(Cerceve cerceve);

    void deleteCerceve(Long id);
    
    Cerceve updateCerceve(Cerceve cerceve, Long id);

    List<Cerceve> getAllCerceve();
}
