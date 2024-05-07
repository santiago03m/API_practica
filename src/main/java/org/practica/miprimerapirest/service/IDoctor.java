package org.practica.miprimerapirest.service;

import org.practica.miprimerapirest.model.entity.Doctor;

public interface IDoctor {
    Doctor save(Doctor doctor);

    Doctor findById(Long Id);

    void delete(Doctor doctor);
}
