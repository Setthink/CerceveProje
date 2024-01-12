package com.setthink.cerceveproje.repository;

import com.setthink.cerceveproje.entity.Siparis;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SiparisRepository extends CrudRepository<Siparis, Long> {

    List<Siparis> findByMusteriId(Long musteriId);

}
