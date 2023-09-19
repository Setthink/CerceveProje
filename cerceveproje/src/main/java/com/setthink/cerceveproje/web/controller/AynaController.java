package com.setthink.cerceveproje.web.controller;

import com.setthink.cerceveproje.entity.Ayna;
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
    AynaService aynaService;

    @GetMapping("/{id}")
    public ResponseEntity<Ayna> getAyna(@PathVariable Long id){
        return new ResponseEntity<>(aynaService.getAyna(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ayna> saveAyna(@Valid @RequestBody Ayna ayna){
        return new ResponseEntity<>(aynaService.saveAyna(ayna),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAyna(@PathVariable Long id){
        aynaService.deleteAyna(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ayna>> getAllAyna(){
        return new ResponseEntity<>(aynaService.getAllAyna(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ayna> updateAyna(@PathVariable Long id, @Valid @RequestBody Ayna ayna){
        return new ResponseEntity<>(aynaService.updateAyna(ayna,id), HttpStatus.OK);
    }
}
