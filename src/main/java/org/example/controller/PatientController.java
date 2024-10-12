package org.example.controller;

import org.example.model.Nurse;
import org.example.model.User;
import org.example.model.Patient;
import org.example.model.Package;
import org.example.repository.NurseRepository;
import org.example.repository.PackageRepository;
import org.example.repository.PatientRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/patient")
@CrossOrigin("*")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    NurseRepository nurseRepository;
    @Autowired
    PackageRepository packageRepository;

    @GetMapping
    public ResponseEntity<Object> allPatients() {
        return new ResponseEntity<>(patientRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getPatientByUserId(@PathVariable Long userId) {

        Optional<Patient> patient = patientRepository.findByUser_UserId(userId);

        if (patient.isEmpty())
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isEmpty())
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Object> savePatient(@PathVariable Long userId, @RequestBody Patient patient) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty())
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

        patient.setUser(user.get());

        return new ResponseEntity<>(patientRepository.save(patient), HttpStatus.CREATED);
    }

    @PutMapping({"/{id}","/{id}/nurse/{nurseId}"})
    public ResponseEntity<Object> updatePatient(
            @PathVariable(name="id") Long userId,
            @PathVariable(required = false) Long nurseId,
            @RequestBody(required = false) Patient patient,
            @RequestParam(required = false) Long packageId) {
        Patient updatedPatient = patientRepository.findById(userId).get();
        System.out.println(updatedPatient);
        //Updating patient details through @requestbody parameters
        if (patient !=null) {
            if (patient.getNextOfKinName() != null)
                updatedPatient.setNextOfKinName(patient.getNextOfKinName());
            if (patient.getNextOfKinPhone() != null)
                updatedPatient.setNextOfKinPhone(patient.getNextOfKinPhone());
            if (patient.getMedicalRecords() != null)
                updatedPatient.setMedicalRecords(patient.getMedicalRecords());
            if (patient.getMedicalPrescriptions() != null)
                updatedPatient.setMedicalPrescriptions(patient.getMedicalPrescriptions());
            if (patient.getAdditionalNotes() != null)
                updatedPatient.setAdditionalNotes(patient.getAdditionalNotes());
            if (patient.getStartDate() != null)
                updatedPatient.setStartDate(patient.getStartDate());
            if (patient.getEndDate() != null)
                updatedPatient.setEndDate(patient.getEndDate());
        }
        //Update assigned nurseid through optional @pathVariable parameter
        if(nurseId != null) {
            Nurse nurse = nurseRepository.findById(nurseId).get();
            updatedPatient.setNurse(nurse);
        }

        //Update package chosen through query parameters (@RequestParam)
        if(packageId != null){
            Package _package = packageRepository.findById(packageId).get();
            updatedPatient.set_package(_package);
        }
        return new ResponseEntity<>(patientRepository.save(updatedPatient), HttpStatus.OK);
    }
    @PutMapping({"/{patientId}/remove-nurse"})
    public ResponseEntity<Object> updatePatient(@PathVariable Long patientId){
        Patient patient = patientRepository.findById(patientId).get();
        patient.setNurse(null);
        return new ResponseEntity<>("Nurse removed",HttpStatus.OK);
    };


}
