package org.practica.miprimerapirest.service.Impl;

import org.practica.miprimerapirest.model.entity.Doctor;
import org.practica.miprimerapirest.service.IDoctor;
import org.practica.miprimerapirest.model.dao.DoctorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DoctorImpl implements IDoctor {

    private final DoctorDao doctorDao;

    @Autowired
    public DoctorImpl(DoctorDao doctorDao){
        this.doctorDao = doctorDao;
    }

    @Transactional
    @Override
    public Doctor save(Doctor doctor) {
        return doctorDao.save(doctor);
    }

    @Transactional(readOnly = true)
    @Override
    public Doctor findById(Long Id) {
        return doctorDao.findById(Id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Doctor doctor) {
        doctorDao.delete(doctor);
    }
}