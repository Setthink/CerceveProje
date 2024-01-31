package com.setthink.cerceveproje.service;

import com.setthink.cerceveproje.model.request.AynaRequest;
import com.setthink.cerceveproje.model.response.AynaResponse;

import java.util.List;

public interface AynaService {

    AynaResponse getAyna(Long id);

    AynaResponse saveAyna(AynaRequest aynaRequest);

    void deleteAyna(Long id);

    AynaResponse updateAyna(AynaRequest aynaRequest, Long id);

    List<AynaResponse> getAllAyna();
}
