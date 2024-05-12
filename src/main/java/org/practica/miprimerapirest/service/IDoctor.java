package org.practica.miprimerapirest.service;

import org.practica.miprimerapirest.model.dto.DoctorDto;
import org.practica.miprimerapirest.model.entity.Doctor;

public interface IDoctor {
    Doctor save(DoctorDto doctorDto);

    Doctor findById(Long Id);

    void delete(Doctor doctor);
}
