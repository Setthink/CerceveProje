package com.setthink.cerceveproje.service;

import com.setthink.cerceveproje.entity.Paspartu;

import java.util.List;

public interface PaspartuService {

    Paspartu getPaspartu(Long id);

    Paspartu savePaspartu(Paspartu paspartu);

    void deletePaspartu(Long id);

    Paspartu updatePaspartu(Paspartu paspartu, Long id);

    List<Paspartu> getAllPaspartu();

}
