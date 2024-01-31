package com.setthink.cerceveproje.service;

import com.setthink.cerceveproje.model.request.CerceveRequest;
import com.setthink.cerceveproje.model.response.CerceveResponse;

import java.util.List;

public interface CerceveService {
    CerceveResponse getCerceve(Long id);

    CerceveResponse saveCerceve(CerceveRequest cerceve);

    void deleteCerceve(Long id);

    CerceveResponse updateCerceve(CerceveRequest cerceve, Long id);

    List<CerceveResponse> getAllCerceve();

}
