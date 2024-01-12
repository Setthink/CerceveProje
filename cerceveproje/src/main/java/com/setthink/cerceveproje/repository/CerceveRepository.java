package com.setthink.cerceveproje.repository;

import com.setthink.cerceveproje.entity.Cerceve;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CerceveRepository extends CrudRepository<Cerceve, Long> {

    List<Cerceve> findByCerceveKoduIn(List<String> ListCerceveKodu);

}
