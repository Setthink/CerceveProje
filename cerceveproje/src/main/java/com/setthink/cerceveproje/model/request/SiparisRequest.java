package com.setthink.cerceveproje.model.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiparisRequest {

    @Min(value = 0, message = "En 0'dan küçük olamaz")
    @NotNull
    private float en;

    @Min(value = 0, message = "Boy 0'dan küçük olamaz")
    @NotNull
    private float boy;

    private List<String> cerceveKodu;

    private List<String> camKodu;

    private List<String> paspartuKodu;

    private List<String> aynaKodu;

    private Long musteriId;

    private String siparisNot;

    private LocalDate siparisTarih;

    private float siparisFiyat;

}
