package com.setthink.cerceveproje.service;

import com.setthink.cerceveproje.entity.Cam;
import com.setthink.cerceveproje.model.request.CamRequest;
import com.setthink.cerceveproje.model.response.CamResponse;

import java.util.List;

public interface CamService {

    CamResponse getCam(Long id);

    CamResponse saveCam(CamRequest camRequest);

    CamResponse updateCam(CamRequest camRequest, Long id);

    void deleteCam(Long id);

    List<CamResponse> getAllCam();

}
