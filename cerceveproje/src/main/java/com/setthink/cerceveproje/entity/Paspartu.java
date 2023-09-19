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
@Table(name = "paspartu")
public class Paspartu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Paspartu kodu boş olamaz")
    @NonNull
    @Column(name = "paspartukodu", nullable = false, unique = true)
    private String paspartuKodu;



    @Column(name = "paspartufiyat", nullable = false)
    @NonNull
    @Min(value = 0, message = "Paspartu boyutu 0'dan küçük olamaz")
    private float paspartuFiyat;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },mappedBy = "paspartular")
    @JsonIgnore
    private List<Siparis> Siparisler;

}
