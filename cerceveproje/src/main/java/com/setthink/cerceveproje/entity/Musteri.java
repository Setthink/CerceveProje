package com.setthink.cerceveproje.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "musteri")
public class Musteri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Müşteri adı boş olamaz")
    @NonNull
    @Column(name = "musteriadi", nullable = false)
    private String musteriAdi;

    @NotBlank(message = "Müşteri numarası boş olamaz")
    @NonNull
    @Column(name = "musterinumara", nullable = false)
    private String musteriNumara;

    @JsonIgnore
    @OneToMany(mappedBy = "musteri", cascade = CascadeType.ALL)
    private List<Siparis> Siparisler;
}
