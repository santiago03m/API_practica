package org.practica.miprimerapirest.controller;

import org.practica.miprimerapirest.model.dto.DoctorDto;
import org.practica.miprimerapirest.model.entity.Doctor;
import org.practica.miprimerapirest.model.payload.MensajeReponse;
import org.practica.miprimerapirest.service.IDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class DoctorController {
    private final IDoctor doctorService;

    @Autowired
    public DoctorController(IDoctor doctorService) {
        this.doctorService = doctorService;
    }

    private DoctorDto convertToDto(Doctor doctor) {
        return DoctorDto.builder().doctorId(doctor.getDoctorId())
                .nombre(doctor.getNombre())
                .disponible(doctor.getDisponible())
                .build();
    }

    @PostMapping("doctor")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDto create(@RequestBody DoctorDto doctorDto) {
        Doctor doctorSave = doctorService.save(doctorDto);
        return convertToDto(doctorSave);
    }

    @PutMapping("doctor")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDto update(@RequestBody DoctorDto doctorDto) {
        Doctor doctorUpdate = doctorService.save(doctorDto);
        return convertToDto(doctorUpdate);
    }

    @DeleteMapping("doctor/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Doctor doctorDelete = doctorService.findById(id);
            doctorService.delete(doctorDelete);
            return new ResponseEntity<>(doctorDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MensajeReponse.builder()
                    .mensaje(exDt.getMessage())
                    .objeto(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("doctor/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorDto showById(@PathVariable Long id) {
        Doctor doctor = doctorService.findById(id);
        return convertToDto(doctor);
    }

    @GetMapping("doctores")
    public ResponseEntity<?> findAll() {
        try {
            List<Doctor> doctores = doctorService.findAll();
            List<DoctorDto> doctoresDto = doctores.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(doctoresDto, HttpStatus.OK);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MensajeReponse.builder()
                    .mensaje(exDt.getMessage())
                    .objeto(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
