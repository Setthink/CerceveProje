package com.setthink.cerceveproje.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cerceve")
public class Cerceve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Cerceve kodu boş olamaz")
    @NonNull
    @Column(name = "cercevekodu", nullable = false, unique = true)
    private String cerceveKodu;


    @Column(name = "cerceveboyutu", nullable = false)
    @NonNull
    @Min(value = 0, message = "Cerceve boyutu 0'dan küçük olamaz")
    private float cerceveBoyutu;

    @Column(name = "cercevegenislik", nullable = false)
    @NonNull
    @Min(value = 0, message = "Cerceve genişliği 0'dan küçük olamaz")
    private float cerceveGenislik;

    @Column(name = "cercevefiyat", nullable = false)
    @NonNull
    @Min(value = 0, message = "Cerceve fiyatı 0'dan küçük olamaz")
    private float cerceveFiyat;



    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            },mappedBy = "cerceveler")
    @JsonIgnore
    private List<Siparis> Siparisler;
}
