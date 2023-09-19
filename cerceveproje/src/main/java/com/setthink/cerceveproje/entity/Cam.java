package com.setthink.cerceveproje.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cam")
public class Cam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Cam kodu boş olamaz")
    @NonNull
    @Column(name = "camkodu", nullable = false, unique = true)
    private String camKodu;

    @NotBlank(message = "Cam modeli boş olamaz")
    @NonNull
    @Column(name = "cammodel", nullable = false)
    private String camModel;

    @Column(name = "camfiyat", nullable = false)
    @NonNull
    @Min(value = 0, message = "Cam fiyatı 0'dan küçük olamaz")
    private float camFiyat;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },mappedBy = "camlar")
    @JsonIgnore
    private List<Siparis> Siparisler;
}
