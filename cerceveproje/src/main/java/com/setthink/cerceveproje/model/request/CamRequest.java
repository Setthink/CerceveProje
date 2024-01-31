package com.setthink.cerceveproje.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CamRequest {

    @NotBlank(message = "Cam kodu boş olamaz")
    private String camKodu;

    @NotBlank(message = "Cam modeli boş olamaz")
    private String camModel;

    @Min(value = 0, message = "Cam fiyatı 0'dan küçük olamaz")
    @NotNull
    private float camFiyat;

}
