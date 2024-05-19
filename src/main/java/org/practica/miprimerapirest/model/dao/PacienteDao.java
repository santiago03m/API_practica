package org.practica.miprimerapirest.model.dao;

import org.hibernate.query.criteria.JpaDerivedRoot;
import org.practica.miprimerapirest.model.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteDao extends JpaRepository<Paciente, Long> {
}