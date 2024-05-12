package org.practica.miprimerapirest.service;

import org.practica.miprimerapirest.model.dto.DoctorDto;
import org.practica.miprimerapirest.model.entity.Doctor;

import java.util.List;

public interface IDoctor {
    Doctor save(DoctorDto doctorDto);

    Doctor findById(Long Id);

    List<Doctor> findAll();

    void delete(Doctor doctor);
}
