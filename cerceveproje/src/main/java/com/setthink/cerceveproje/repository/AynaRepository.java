package com.setthink.cerceveproje.repository;

import com.setthink.cerceveproje.entity.Ayna;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AynaRepository extends CrudRepository<Ayna, Long> {
    Optional<Ayna> getAynaByAynaKodu(String aynaKodu);

}
