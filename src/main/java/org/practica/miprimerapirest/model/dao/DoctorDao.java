package org.practica.miprimerapirest.model.dao;

import org.practica.miprimerapirest.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DoctorDao extends JpaRepository<Doctor, Long> {

}