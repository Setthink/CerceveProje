package com.setthink.cerceveproje.repository;

import com.setthink.cerceveproje.entity.Cam;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CamRepository extends CrudRepository<Cam, Long> {

    List<Cam> findByCamKoduIn(List<String> ListCamKodu);
}
