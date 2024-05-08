package org.practica.miprimerapirest.controller;

import org.practica.miprimerapirest.model.entity.Doctor;
import org.practica.miprimerapirest.service.IDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class DoctorController {
    private final IDoctor doctorService;

    @Autowired
    public DoctorController(IDoctor doctorService){
        this.doctorService = doctorService;
    }

    @PostMapping("doctor")
    public Doctor create(@RequestBody Doctor doctor){
        return doctorService.save(doctor);
    }

    @PutMapping("doctor")
    public Doctor update(@RequestBody Doctor doctor){
        return doctorService.save(doctor);
    }

    @DeleteMapping("doctor/{id}")
    public void delete(@PathVariable Long id){
        Doctor doctorDelete = doctorService.findById(id);
        doctorService.delete(doctorDelete);
    }

    @GetMapping("doctor/{id}")
    public Doctor showById(@PathVariable Long id){
        return doctorService.findById(id);
    }
}
