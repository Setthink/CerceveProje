package com.setthink.cerceveproje.web.controller;

import com.setthink.cerceveproje.entity.Cam;
import com.setthink.cerceveproje.service.CamService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cam")
public class CamController {

    CamService camService;

    @GetMapping("/{id}")
    public ResponseEntity<Cam> getCam(@PathVariable Long id) {
        return new ResponseEntity<>(camService.getCam(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cam> saveCam(@Valid @RequestBody Cam cam) {
        return new ResponseEntity<>(camService.saveCam(cam), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCam(@PathVariable Long id) {
        camService.deleteCam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cam>> getAllCam() {
        return new ResponseEntity<>(camService.getAllCam(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cam> updateCam(@PathVariable Long id, @Valid @RequestBody Cam cam) {
        return new ResponseEntity<>(camService.updateCam(cam, id), HttpStatus.OK);
    }

}
