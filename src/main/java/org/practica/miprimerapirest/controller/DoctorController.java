package org.practica.miprimerapirest.controller;

import org.practica.miprimerapirest.model.dto.DoctorDto;
import org.practica.miprimerapirest.model.dto.PacienteDto;
import org.practica.miprimerapirest.model.entity.Doctor;
import org.practica.miprimerapirest.model.entity.Paciente;
import org.practica.miprimerapirest.model.payload.MensajeReponse;
import org.practica.miprimerapirest.service.IDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/doctores")
public class DoctorController {
    private final IDoctor doctorServicio;

    @Autowired
    public DoctorController(IDoctor doctorServicio) {
        this.doctorServicio = doctorServicio;
    }

    private DoctorDto convertToDto(Doctor doctor) {
        return DoctorDto.builder().doctorId(doctor.getDoctorId())
                .nombre(doctor.getNombre())
                .disponible(doctor.getDisponible())
                .build();
    }

    @GetMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String formularioCrearDoctor(Model modelo) {
        modelo.addAttribute("doctorNuevo", new DoctorDto());
        return "create_doctor";
    }

    @PostMapping()
    public String create(@ModelAttribute("doctorNuevo") DoctorDto doctorNuevo) {
        doctorNuevo.setDisponible(doctorNuevo.getDisponible() == 1 ? 1 : 0);
        doctorServicio.save(doctorNuevo);
        return "redirect:/doctores";
    }

    @GetMapping("/edit/{id}")
    public String formularioEditarDoctor(@PathVariable Long id, Model modelo) {
        Doctor doctor = doctorServicio.findById(id);
        if (doctor != null) {
            modelo.addAttribute("doctorExistente", convertToDto(doctor));
            return "edit_doctor";
        } else {
            return "redirect:/doctores";
        }
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @Validated @ModelAttribute("doctorExistente") DoctorDto doctorDto) {
        doctorDto.setDoctorId(id);
        doctorServicio.save(doctorDto);
        return "redirect:/doctores";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Doctor doctor = doctorServicio.findById(id);
        if (doctor != null) {
            doctorServicio.delete(doctor);
        }
        return "redirect:/doctores";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable Long id, Model modelo) {
        Doctor doctor = doctorServicio.findById(id);
        if (doctor != null) {
            modelo.addAttribute("doctor", convertToDto(doctor));
            return "show_doctor";
        } else {
            return "redirect:/doctores";
        }
    }

    @GetMapping()
    public String listaDoctores(Model modelo) {
        try {
            List<Doctor> doctores = doctorServicio.findAll();
            List<DoctorDto> doctoresDto = doctores.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
            modelo.addAttribute("doctores", doctoresDto);
            return "doctores";
        } catch (DataAccessException exDt) {
            modelo.addAttribute("error", exDt.getMessage());
            return "doctores";
        }
    }
}
