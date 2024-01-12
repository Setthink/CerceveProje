package com.setthink.cerceveproje.web.controller;

import com.setthink.cerceveproje.entity.Musteri;
import com.setthink.cerceveproje.service.MusteriService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/musteri")
public class MusteriController {

    MusteriService musteriService;

    @GetMapping("{id}")
    public ResponseEntity<Musteri> getMusteri(@PathVariable Long id) {
        return new ResponseEntity<>(musteriService.getMusteri(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Musteri> saveMusteri(@Valid @RequestBody Musteri musteri) {
        return new ResponseEntity<>(musteriService.saveMusteri(musteri), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusteri(@PathVariable Long id) {
        musteriService.deleteMusteri(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Musteri>> getAllMusteri() {
        return new ResponseEntity<>(musteriService.getAllMusteri(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musteri> updateMusteri(@PathVariable Long id, @Valid @RequestBody Musteri musteri) {
        return new ResponseEntity<>(musteriService.updateMusteri(musteri, id), HttpStatus.OK);
    }

}
