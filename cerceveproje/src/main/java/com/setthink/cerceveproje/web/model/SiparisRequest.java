package com.setthink.cerceveproje.web.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiparisRequest {

    @Min(value = 0, message = "En 0'dan küçük olamaz")
    private float en;

    @Min(value = 0, message = "Boy 0'dan küçük olamaz")
    private float boy;

    private List<String> cerceveKodu;

    private List<String> camKodu;

    private List<String> paspartuKodu;

    private List<String> aynaKodu;

    private Long musteriId;

    private String siparisNot;

    @NonNull
    private LocalDate siparisTarih;

    private float siparisFiyat;

}
