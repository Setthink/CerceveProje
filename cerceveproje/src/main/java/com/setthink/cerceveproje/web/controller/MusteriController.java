package com.setthink.cerceveproje.web.controller;

import com.setthink.cerceveproje.model.request.MusteriRequest;
import com.setthink.cerceveproje.model.response.MusteriResponse;
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
    public ResponseEntity<MusteriResponse> getMusteri(@PathVariable Long id) {
        return new ResponseEntity<>(musteriService.getMusteri(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MusteriResponse> saveMusteri(@Valid @RequestBody MusteriRequest musteriRequest) {
        return new ResponseEntity<>(musteriService.saveMusteri(musteriRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusteri(@PathVariable Long id) {
        musteriService.deleteMusteri(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MusteriResponse>> getAllMusteri() {
        return new ResponseEntity<>(musteriService.getAllMusteri(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusteriResponse> updateMusteri(@PathVariable Long id, @Valid @RequestBody MusteriRequest musteriRequest) {
        return new ResponseEntity<>(musteriService.updateMusteri(musteriRequest, id), HttpStatus.OK);
    }

}
