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
public class CerceveRequest {

    @NotBlank(message = "Cerceve kodu boş olamaz")
    private String cerceveKodu;

    @Min(value = 0, message = "Cerceve boyutu 0'dan küçük olamaz")
    @NotNull
    private float cerceveBoyutu;

    @Min(value = 0, message = "Cerceve genişliği 0'dan küçük olamaz")
    @NotNull
    private float cerceveGenislik;

    @Min(value = 0, message = "Cerceve fiyatı 0'dan küçük olamaz")
    @NotNull
    private float cerceveFiyat;

}
