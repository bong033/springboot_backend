package org.example.controller;

import org.example.model.Nurse;
import org.example.model.Patient;
import org.example.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/nurse")
@CrossOrigin("*")
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getNurseByUserId(@PathVariable Long userId) {

        Optional<Nurse> nurse = nurseRepository.findByUser_UserId(userId);

        if (nurse.isEmpty())
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(nurse, HttpStatus.OK);
    }
}
