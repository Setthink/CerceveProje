package com.setthink.cerceveproje.service;

import com.setthink.cerceveproje.model.request.PaspartuRequest;
import com.setthink.cerceveproje.model.response.PaspartuResponse;

import java.util.List;

public interface PaspartuService {

    PaspartuResponse getPaspartu(Long id);

    PaspartuResponse savePaspartu(PaspartuRequest paspartu);

    void deletePaspartu(Long id);

    PaspartuResponse updatePaspartu(PaspartuRequest paspartu, Long id);

    List<PaspartuResponse> getAllPaspartu();

}
