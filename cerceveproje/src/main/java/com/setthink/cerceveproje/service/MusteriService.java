package com.setthink.cerceveproje.service;

import com.setthink.cerceveproje.entity.Musteri;

import java.util.List;

public interface MusteriService {

    Musteri getMusteri(Long id);

    Musteri saveMusteri(Musteri musteri);

    Musteri updateMusteri(Musteri musteri, Long id);

    void deleteMusteri(Long id);

    List<Musteri> getAllMusteri();

}
