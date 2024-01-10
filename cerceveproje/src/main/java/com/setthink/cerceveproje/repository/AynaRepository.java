package com.setthink.cerceveproje.repository;

import com.setthink.cerceveproje.entity.Ayna;
import com.setthink.cerceveproje.entity.Cam;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AynaRepository extends CrudRepository<Ayna, Long> {
    Optional<Ayna> getAynaByAynaKodu(String aynaKodu);
    List<Ayna> findByAynaKoduIn(List<String> ListAynaKodu);
}
