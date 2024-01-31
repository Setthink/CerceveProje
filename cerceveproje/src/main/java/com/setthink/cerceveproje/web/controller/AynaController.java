package com.setthink.cerceveproje.web.controller;

import com.setthink.cerceveproje.model.request.AynaRequest;
import com.setthink.cerceveproje.model.response.AynaResponse;
import com.setthink.cerceveproje.service.AynaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/ayna")
public class AynaController {

    private final AynaService aynaService;

    @GetMapping("/{id}")
    public ResponseEntity<AynaResponse> getAyna(@PathVariable Long id) {
        return new ResponseEntity<>(aynaService.getAyna(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AynaResponse> saveAyna(@Valid @RequestBody AynaRequest aynaRequest) {
        return new ResponseEntity<>(aynaService.saveAyna(aynaRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAyna(@PathVariable Long id) {
        aynaService.deleteAyna(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AynaResponse>> getAllAyna() {
        return new ResponseEntity<>(aynaService.getAllAyna(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AynaResponse> updateAyna(@PathVariable Long id, @Valid @RequestBody AynaRequest aynaRequest) {
        return new ResponseEntity<>(aynaService.updateAyna(aynaRequest, id), HttpStatus.OK);
    }
}
