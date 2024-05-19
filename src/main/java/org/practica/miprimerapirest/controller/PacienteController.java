package org.practica.miprimerapirest.controller;

import org.practica.miprimerapirest.model.dto.PacienteDto;
import org.practica.miprimerapirest.model.entity.Doctor;
import org.practica.miprimerapirest.model.entity.Paciente;
import org.practica.miprimerapirest.model.payload.MensajeReponse;
import org.practica.miprimerapirest.service.IDoctor;
import org.practica.miprimerapirest.service.IPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    private final IPaciente pacienteServicio;
    private final IDoctor doctorServicio;

    @Autowired
    public PacienteController(IPaciente pacienteServicio, IDoctor doctorServicio) {
        this.pacienteServicio = pacienteServicio;
        this.doctorServicio = doctorServicio;
    }

    private PacienteDto convertToDto(Paciente paciente) {
        return PacienteDto.builder()
                .pacienteId(paciente.getPacienteId())
                .nombre(paciente.getNombre())
                .edad(paciente.getEdad())
                .tipoSangre(paciente.getTipoSangre())
                .ultimaCita(paciente.getUltimaCita())
                .doctor(paciente.getDoctor())
                .build();
    }

    @GetMapping
    public String findAll(Model modelo) {
        try {
            List<Paciente> pacientes = pacienteServicio.findAll();
            List<PacienteDto> pacientesDto = pacientes.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
            modelo.addAttribute("pacientes", pacientesDto);
            return "pacientes";
        } catch (DataAccessException exDt) {
            modelo.addAttribute("error", exDt.getMessage());
            return "pacientes";
        }
    }

    @GetMapping("/create")
    public String formularioCrearPaciente(Model modelo){
        List<Doctor> doctores = doctorServicio.findAll();
        modelo.addAttribute("doctores", doctores);
        modelo.addAttribute("pacienteNuevo", new PacienteDto());
        return "create_paciente";
    }

    @PostMapping
    public String create(@ModelAttribute("pacienteNuevo") PacienteDto pacienteNuevo) {
        pacienteServicio.save(pacienteNuevo);
        return "redirect:/pacientes";
    }

    @GetMapping("/edit/{id}")
    public String formularioEditarPaciente(@PathVariable Long id, Model modelo) {
        Paciente paciente = pacienteServicio.findById(id);
        if (paciente != null) {
            modelo.addAttribute("pacienteNuevo", convertToDto(paciente));
            List<Doctor> doctores = doctorServicio.findAll();
            modelo.addAttribute("doctores", doctores);
            return "edit_paciente";
        } else {
            return "redirect:/pacientes";
        }
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("pacienteNuevo") PacienteDto pacienteDto) {
        pacienteDto.setPacienteId(id);
        pacienteServicio.save(pacienteDto);
        return "redirect:/pacientes";
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Paciente paciente = pacienteServicio.findById(id);
        if (paciente != null) {
            pacienteServicio.delete(paciente);
        }
        return "redirect:/pacientes";
    }


    @GetMapping("/{id}")
    public String showById(@PathVariable Long id, Model modelo) {
        Paciente paciente = pacienteServicio.findById(id);
        if (paciente != null) {
            modelo.addAttribute("paciente", convertToDto(paciente));
            return "show_paciente";
        } else {
            return "redirect:/pacientes";
        }
    }
}
