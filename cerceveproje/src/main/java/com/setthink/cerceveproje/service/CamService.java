package com.setthink.cerceveproje.service;

import com.setthink.cerceveproje.entity.Cam;

import java.util.List;

public interface CamService {

    Cam getCam(Long id);

    Cam saveCam(Cam cam);

    Cam updateCam(Cam cam, Long id);

    void deleteCam(Long id);

    List<Cam> getAllCam();

}
