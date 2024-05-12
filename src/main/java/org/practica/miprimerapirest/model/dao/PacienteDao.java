package org.practica.miprimerapirest.model.dao;

import org.practica.miprimerapirest.model.entity.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteDao extends CrudRepository<Paciente, Long> {
}