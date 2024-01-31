package com.setthink.cerceveproje.web.controller;

import com.setthink.cerceveproje.model.request.CerceveRequest;
import com.setthink.cerceveproje.model.response.CerceveResponse;
import com.setthink.cerceveproje.service.CerceveService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cerceve")
public class CerceveController {

    CerceveService cerceveService;

    @GetMapping("/{id}")
    public ResponseEntity<CerceveResponse> getCerceve(@PathVariable Long id) {
        return new ResponseEntity<>(cerceveService.getCerceve(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CerceveResponse> saveCerceve(@Valid @RequestBody CerceveRequest cerceveRequest) {
        return new ResponseEntity<>(cerceveService.saveCerceve(cerceveRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCerceve(@PathVariable Long id) {
        cerceveService.deleteCerceve(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CerceveResponse>> getAllCerceve() {
        return new ResponseEntity<>(cerceveService.getAllCerceve(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CerceveResponse> updateCerceve(@PathVariable Long id, @Valid @RequestBody CerceveRequest cerceveRequest) {
        return new ResponseEntity<>(cerceveService.updateCerceve(cerceveRequest, id), HttpStatus.OK);
    }

}
