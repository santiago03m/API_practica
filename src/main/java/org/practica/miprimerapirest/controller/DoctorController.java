package org.practica.miprimerapirest.controller;

import org.practica.miprimerapirest.model.dto.DoctorDto;
import org.practica.miprimerapirest.model.entity.Doctor;
import org.practica.miprimerapirest.service.IDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDto create(@RequestBody DoctorDto doctorDto){
        Doctor doctorSave = doctorService.save(doctorDto);
        return DoctorDto.builder()
                .doctorId(doctorSave.getDoctorId())
                .nombre(doctorSave.getNombre())
                .disponible(doctorSave.getDisponible())
                .build();
    }

    @PutMapping("doctor")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDto update(@RequestBody DoctorDto doctorDto){
        Doctor doctorUpdate = doctorService.save(doctorDto);
        return DoctorDto.builder()
                .doctorId(doctorUpdate.getDoctorId())
                .nombre(doctorUpdate.getNombre())
                .disponible(doctorUpdate.getDisponible())
                .build();
    }

    @DeleteMapping("doctor/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        Doctor doctorDelete = doctorService.findById(id);
        doctorService.delete(doctorDelete);
    }

    @GetMapping("doctor/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorDto showById(@PathVariable Long id){
        Doctor doctor = doctorService.findById(id);
        return DoctorDto.builder()
                .doctorId(doctor.getDoctorId())
                .nombre(doctor.getNombre())
                .disponible(doctor.getDisponible())
                .build();
    }
}
