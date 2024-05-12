package org.practica.miprimerapirest.service;

import org.practica.miprimerapirest.model.dto.PacienteDto;
import org.practica.miprimerapirest.model.entity.Paciente;
import java.util.List;

public interface IPaciente {
    Paciente save(PacienteDto pacienteDto);

    Paciente findById(Long Id);

    List<Paciente> findAll();

    void delete(Paciente paciente);
}