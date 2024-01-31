package com.setthink.cerceveproje.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CerceveResponse {

    private Long id;

    private String cerceveKodu;

    private float cerceveBoyutu;

    private float cerceveGenislik;

    private float cerceveFiyat;

}
