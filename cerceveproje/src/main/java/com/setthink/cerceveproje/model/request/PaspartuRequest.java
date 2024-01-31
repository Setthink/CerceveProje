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
public class PaspartuRequest {

    @NotBlank(message = "Paspartu kodu boş olamaz")
    private String paspartuKodu;

    @Min(value = 0, message = "Paspartu boyutu 0'dan küçük olamaz")
    @NotNull
    private float paspartuFiyat;

}
