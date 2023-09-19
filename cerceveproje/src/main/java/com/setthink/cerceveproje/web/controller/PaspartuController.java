package com.setthink.cerceveproje.web.controller;

import com.setthink.cerceveproje.entity.Paspartu;
import com.setthink.cerceveproje.service.PaspartuService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/paspartu")
public class PaspartuController {
    PaspartuService paspartuService;

    @GetMapping("/{id}")
    public ResponseEntity<Paspartu> getPaspartu(@PathVariable Long id) {
        return new ResponseEntity<>(paspartuService.getPaspartu(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Paspartu> savePaspartu(@Valid @RequestBody Paspartu paspartu) {
        return new ResponseEntity<>(paspartuService.savePaspartu(paspartu), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaspartu(@PathVariable Long id) {
        paspartuService.deletePaspartu(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Paspartu>> getAllPaspartu() {
        return new ResponseEntity<>(paspartuService.getAllPaspartu(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paspartu> updatePaspartu(@PathVariable Long id, @Valid @RequestBody Paspartu paspartu) {
        return new ResponseEntity<>(paspartuService.updatePaspartu(paspartu, id), HttpStatus.OK);
    }
}
