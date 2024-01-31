package com.setthink.cerceveproje.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusteriResponse {

    private Long id;

    private String musteriAdi;

    private String musteriNumara;

}
