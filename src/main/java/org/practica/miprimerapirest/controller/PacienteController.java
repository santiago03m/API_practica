package org.practica.miprimerapirest.controller;

import org.practica.miprimerapirest.model.entity.Paciente;
import org.practica.miprimerapirest.service.IPaciente;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Paciente create(@RequestBody Paciente paciente){
        return pacienteServicio.save(paciente);
    }

    @PutMapping("paciente")
    public Paciente update(@RequestBody Paciente paciente){
        return pacienteServicio.save(paciente);
    }

    @DeleteMapping("paciente/{id}")
    public void delete(@PathVariable Long id){
        Paciente pacienteDelete = pacienteServicio.findById(id);
        pacienteServicio.delete(pacienteDelete);
    }

    @GetMapping("paciente/{id}")
    public Paciente showById(@PathVariable Long id){
        return pacienteServicio.findById(id);
    }
}