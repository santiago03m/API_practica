package org.practica.miprimerapirest.controller;

import org.practica.miprimerapirest.model.dto.PacienteDto;
import org.practica.miprimerapirest.model.entity.Paciente;
import org.practica.miprimerapirest.model.payload.MensajeReponse;
import org.practica.miprimerapirest.service.IPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PacienteController {
    private final IPaciente pacienteServicio;

    @Autowired
    public PacienteController(IPaciente pacienteServicio) {
        this.pacienteServicio = pacienteServicio;
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

    @PostMapping("paciente")
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteDto create(@RequestBody PacienteDto pacienteDto) {
        Paciente pacienteSave = pacienteServicio.save(pacienteDto);
        return convertToDto(pacienteSave);
    }

    @PutMapping("paciente")
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteDto update(@RequestBody PacienteDto pacienteDto) {
        Paciente pacienteUpdate = pacienteServicio.save(pacienteDto);
        return convertToDto(pacienteUpdate);
    }

    @DeleteMapping("paciente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Paciente pacienteDelete = pacienteServicio.findById(id);
            pacienteServicio.delete(pacienteDelete);
            return new ResponseEntity<>(pacienteDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MensajeReponse.builder()
                    .mensaje(exDt.getMessage())
                    .objeto(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("paciente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacienteDto showById(@PathVariable Long id) {
        Paciente paciente = pacienteServicio.findById(id);
        return convertToDto(paciente);
    }

    @GetMapping("/pacientes")
    public String findAll(Model modelo) {
        try {
            List<Paciente> pacientes = pacienteServicio.findAll();
            List<PacienteDto> pacientesDto = pacientes.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
            modelo.addAttribute("pacientes", pacientesDto);
            return "pacientes";
        } catch (DataAccessException exDt) {
            modelo.addAttribute(MensajeReponse.builder()
                    .mensaje(exDt.getMessage())
                    .objeto(null)
                    .build());
            return "pacientes";
        }
    }
}