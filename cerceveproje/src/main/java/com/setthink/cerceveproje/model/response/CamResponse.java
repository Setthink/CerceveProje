package com.setthink.cerceveproje.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CamResponse {

    private Long id;

    private String camKodu;

    private String camModel;

    private float camFiyat;

}
