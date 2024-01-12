package com.setthink.cerceveproje.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "ayna")
public class Ayna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ayna kodu boş olamaz")
    @NonNull
    @Column(name = "aynakodu", nullable = false, unique = true)
    private String aynaKodu;

    @Column(name = "aynafiyat", nullable = false)
    @NonNull
    @Min(value = 0, message = "Ayna fiyatı 0'dan küçük olamaz")
    private float aynaFiyat;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            }, mappedBy = "aynalar")
    @JsonIgnore
    private List<Siparis> Siparisler;

}
