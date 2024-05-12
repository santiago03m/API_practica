package org.practica.miprimerapirest.service;

import org.practica.miprimerapirest.model.dto.PacienteDto;
import org.practica.miprimerapirest.model.entity.Paciente;

public interface IPaciente {
    Paciente save(PacienteDto pacienteDto);
    Paciente findById(Long Id);
    void delete(Paciente paciente);
}