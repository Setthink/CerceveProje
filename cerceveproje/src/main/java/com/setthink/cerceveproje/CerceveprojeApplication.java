package com.setthink.cerceveproje;

import com.setthink.cerceveproje.entity.*;
import com.setthink.cerceveproje.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
public class CerceveprojeApplication implements CommandLineRunner {
    @Autowired
    CerceveRepository cerceveRepository;

    @Autowired
    PaspartuRepository paspartuRepository;

    @Autowired
    CamRepository camRepository;

    @Autowired
    MusteriRepository musteriRepository;

    @Autowired
    AynaRepository aynaRepository;


    public static void main(String[] args) {
        SpringApplication.run(CerceveprojeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Cerceve[] cerceves = new Cerceve[]{
                new Cerceve("abc",15f,15f,15f),
                new Cerceve("def",15f,15f,15f),
                new Cerceve("ghi",15f,15f,15f),
        };
        cerceveRepository.saveAll(Arrays.asList(cerceves));

        Paspartu[] paspartus = new Paspartu[]{
                new Paspartu("abc",15f),
                new Paspartu("def",15f),
                new Paspartu("ghi",15f),
        };
        paspartuRepository.saveAll(Arrays.asList(paspartus));

        Cam[] cams = new Cam[]{
                new Cam("abc","Mat",15f),
                new Cam("def","Mat",15f),
                new Cam("ghi","Düz",15f),
        };
        camRepository.saveAll(Arrays.asList(cams));

        Musteri[] musteris = new Musteri[]{
                new Musteri("Mehmet","123456789"),
                new Musteri("Ali","123456789"),
                new Musteri("Veli","123456789"),
        };
        musteriRepository.saveAll(Arrays.asList(musteris));

        Ayna[] aynas = new Ayna[]{
                new Ayna("abc",15f),
                new Ayna("def",15f),
                new Ayna("ghi",15f)
        };
        aynaRepository.saveAll(Arrays.asList(aynas));
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods(
                        "GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"
                );
            }
        };
    }

}
