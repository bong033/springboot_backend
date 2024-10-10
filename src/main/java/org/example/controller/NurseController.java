package org.example.controller;

import org.example.model.Nurse;
import org.example.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nurse")
public class NurseController {
    @Autowired
    NurseRepository nurseRepository;

    @PostMapping
    public ResponseEntity<Object> saveNurse(@RequestBody Nurse nurse){
        return new ResponseEntity<>(nurseRepository.save(nurse), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> allNurse() {
        return new ResponseEntity<>(nurseRepository.findAll(), HttpStatus.OK);
    }

}
