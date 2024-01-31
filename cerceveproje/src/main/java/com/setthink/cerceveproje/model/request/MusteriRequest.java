package com.setthink.cerceveproje.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusteriRequest {

    @NotBlank(message = "Müşteri adı boş olamaz")
    private String musteriAdi;

    @NotBlank(message = "Müşteri numarası boş olamaz")
    private String musteriNumara;

}
