package com.setthink.cerceveproje.web.controller;

import com.setthink.cerceveproje.entity.Siparis;
import com.setthink.cerceveproje.web.model.SiparisRequest;
import com.setthink.cerceveproje.service.SiparisService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/siparis")
public class SiparisController {
    SiparisService siparisService;
    @GetMapping("/{id}")
    public ResponseEntity<Siparis> getSiparis(@PathVariable Long id){
        return new ResponseEntity<>(siparisService.getSiparis(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Siparis> saveSiparis(@Valid @RequestBody SiparisRequest siparis){
        return new ResponseEntity<>(siparisService.saveSiparis(siparis),HttpStatus.CREATED);
    }


    @GetMapping("/musteri/{musteriId}")
    public ResponseEntity<List<Siparis>> getSiparisByMusteriId(@PathVariable Long musteriId){
        return new ResponseEntity<>(siparisService.getSiparisByMusteriId(musteriId),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Siparis>> getAllSiparis(){
        return new ResponseEntity<>(siparisService.getAllSiparis(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Siparis> updateSiparis(@PathVariable Long id, @Valid @RequestBody SiparisRequest siparis){
        return new ResponseEntity<>(siparisService.updateSiparis(siparis,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSiparis(@PathVariable Long id){
        siparisService.deleteSiparis(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
