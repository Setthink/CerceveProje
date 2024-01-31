package com.setthink.cerceveproje.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AynaResponse {

    private Long id;

    private String aynaKodu;

    private float aynaFiyat;

}
