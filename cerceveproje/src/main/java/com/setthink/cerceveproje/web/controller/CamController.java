package com.setthink.cerceveproje.web.controller;

import com.setthink.cerceveproje.model.request.CamRequest;
import com.setthink.cerceveproje.model.response.CamResponse;
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
    public ResponseEntity<CamResponse> getCam(@PathVariable Long id) {
        return new ResponseEntity<>(camService.getCam(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CamResponse> saveCam(@Valid @RequestBody CamRequest camRequest) {
        return new ResponseEntity<>(camService.saveCam(camRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCam(@PathVariable Long id) {
        camService.deleteCam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CamResponse>> getAllCam() {
        return new ResponseEntity<>(camService.getAllCam(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CamResponse> updateCam(@PathVariable Long id, @Valid @RequestBody CamRequest camRequest) {
        return new ResponseEntity<>(camService.updateCam(camRequest, id), HttpStatus.OK);
    }

}
