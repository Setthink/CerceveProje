package com.setthink.cerceveproje.service;

import com.setthink.cerceveproje.model.request.MusteriRequest;
import com.setthink.cerceveproje.model.response.MusteriResponse;

import java.util.List;

public interface MusteriService {

    MusteriResponse getMusteri(Long id);

    MusteriResponse saveMusteri(MusteriRequest musteri);

    MusteriResponse updateMusteri(MusteriRequest musteri, Long id);

    void deleteMusteri(Long id);

    List<MusteriResponse> getAllMusteri();

}
