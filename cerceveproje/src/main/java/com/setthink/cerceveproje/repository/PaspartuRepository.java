package com.setthink.cerceveproje.repository;

import com.setthink.cerceveproje.entity.Paspartu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaspartuRepository extends CrudRepository<Paspartu, Long> {

    List<Paspartu> findByPaspartuKoduIn(List<String> ListPaspartuKod);
}
