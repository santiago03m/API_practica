package org.practica.miprimerapirest.controller;

import org.practica.miprimerapirest.model.dto.PacienteDto;
import org.practica.miprimerapirest.model.entity.Paciente;
import org.practica.miprimerapirest.service.IPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PacienteController {
    private final IPaciente pacienteServicio;

    @Autowired
    public PacienteController(IPaciente pacienteServicio){
        this.pacienteServicio = pacienteServicio;
    }

    @PostMapping("paciente")
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteDto create(@RequestBody PacienteDto pacienteDto){
        Paciente pacienteSave = pacienteServicio.save(pacienteDto);
        return PacienteDto.builder()
                .pacienteId(pacienteSave.getPacienteId())
                .nombre(pacienteSave.getNombre())
                .edad(pacienteSave.getEdad())
                .tipoSangre(pacienteSave.getTipoSangre())
                .ultimaCita(pacienteSave.getUltimaCita())
                .doctor(pacienteSave.getDoctor())
                .build();
    }

    @PutMapping("paciente")
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteDto update(@RequestBody PacienteDto pacienteDto){
        Paciente pacienteUpdate = pacienteServicio.save(pacienteDto);
        return PacienteDto.builder()
                .pacienteId(pacienteUpdate.getPacienteId())
                .nombre(pacienteUpdate.getNombre())
                .edad(pacienteUpdate.getEdad())
                .tipoSangre(pacienteUpdate.getTipoSangre())
                .ultimaCita(pacienteUpdate.getUltimaCita())
                .doctor(pacienteUpdate.getDoctor())
                .build();
    }

    @DeleteMapping("paciente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        Paciente pacienteDelete = pacienteServicio.findById(id);
        pacienteServicio.delete(pacienteDelete);
    }

    @GetMapping("paciente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacienteDto showById(@PathVariable Long id){
        Paciente paciente = pacienteServicio.findById(id);
        return PacienteDto.builder()
                .pacienteId(paciente.getPacienteId())
                .nombre(paciente.getNombre())
                .edad(paciente.getEdad())
                .tipoSangre(paciente.getTipoSangre())
                .ultimaCita(paciente.getUltimaCita())
                .doctor(paciente.getDoctor())
                .build();
    }
}