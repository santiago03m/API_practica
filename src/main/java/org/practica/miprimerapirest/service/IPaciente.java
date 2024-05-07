package org.practica.miprimerapirest.service;

import org.practica.miprimerapirest.model.entity.Paciente;

public interface IPaciente {
    Paciente save(Paciente paciente);
    Paciente findById(Long Id);
    void delete(Paciente paciente);
}
