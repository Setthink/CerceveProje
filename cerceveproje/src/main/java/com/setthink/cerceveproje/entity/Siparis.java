package com.setthink.cerceveproje.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "siparis")
public class Siparis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "en", nullable = false)
    @NonNull
    @Min(value = 0, message = "En 0'dan küçük olamaz")
    private float en;

    @Column(name = "boy", nullable = false)
    @NonNull
    @Min(value = 0, message = "Boy 0'dan küçük olamaz")
    private float boy;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "siparis_cerceve",
            joinColumns = { @JoinColumn(name = "siparis_id") },
            inverseJoinColumns = { @JoinColumn(name = "cerceve_id") })
    private List<Cerceve> cerceveler;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "siparis_cam",
            joinColumns = { @JoinColumn(name = "siparis_id") },
            inverseJoinColumns = { @JoinColumn(name = "cam_id") })
    private List<Cam> camlar;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "siparis_paspartu",
            joinColumns = { @JoinColumn(name = "siparis_id") },
            inverseJoinColumns = { @JoinColumn(name = "paspartu_id") })
    private List<Paspartu> paspartular;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "siparis_ayna",
            joinColumns = { @JoinColumn(name = "siparis_id") },
            inverseJoinColumns = { @JoinColumn(name = "ayna_id") })
    private List<Ayna> aynalar;



    @ManyToOne(optional = false)
    @JoinColumn(name = "musteri_id", nullable = false)
    private Musteri musteri;

    @Column(name = "siparis_not")
    private String siparisNot;

    @NonNull
    @Column(name = "siparis_tarih", nullable = false)
    private LocalDate siparisTarih;

    @Column(name = "siparis_fiyat", nullable = false)
    @NonNull
    @Min(value = 0, message = "Sipariş fiyatı 0'dan küçük olamaz")
    private float siparisFiyat;

}
