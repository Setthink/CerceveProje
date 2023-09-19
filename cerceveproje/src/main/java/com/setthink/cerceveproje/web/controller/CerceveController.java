package com.setthink.cerceveproje.web.controller;

import com.setthink.cerceveproje.entity.Cerceve;
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
    public ResponseEntity<Cerceve> getCerceve(@PathVariable Long id){
        return new ResponseEntity<>(cerceveService.getCerceve(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cerceve> saveCerceve(@Valid @RequestBody Cerceve cerceve){
        return new ResponseEntity<>(cerceveService.saveCerceve(cerceve),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCerceve(@PathVariable Long id){
        cerceveService.deleteCerceve(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cerceve>> getAllCerceve(){
        return new ResponseEntity<>(cerceveService.getAllCerceve(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cerceve> updateCerceve(@PathVariable Long id, @Valid @RequestBody Cerceve cerceve){
        return new ResponseEntity<>(cerceveService.updateCerceve(cerceve,id), HttpStatus.OK);
    }

}
