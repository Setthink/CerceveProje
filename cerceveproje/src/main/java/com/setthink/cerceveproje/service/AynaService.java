package com.setthink.cerceveproje.service;

import com.setthink.cerceveproje.entity.Ayna;

import java.util.List;

public interface AynaService {


    Ayna getAyna(Long id);

    Ayna saveAyna(Ayna ayna);

    void deleteAyna(Long id);

    Ayna updateAyna(Ayna ayna, Long id);

    List<Ayna> getAllAyna();

}
