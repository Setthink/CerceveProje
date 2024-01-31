package com.setthink.cerceveproje.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaspartuResponse {

    private Long id;

    private String paspartuKodu;

    private float paspartuFiyat;

}
