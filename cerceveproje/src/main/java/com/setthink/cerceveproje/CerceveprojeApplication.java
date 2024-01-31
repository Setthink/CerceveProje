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
