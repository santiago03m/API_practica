package org.practica.miprimerapirest.service.Impl;

import org.practica.miprimerapirest.model.dao.PacienteDao;
import org.practica.miprimerapirest.model.dto.DoctorDto;
import org.practica.miprimerapirest.model.entity.Doctor;
import org.practica.miprimerapirest.service.IDoctor;
import org.practica.miprimerapirest.model.dao.DoctorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DoctorImpl implements IDoctor {

    private final DoctorDao doctorDao;

    @Autowired
    public DoctorImpl(DoctorDao doctorDao){
        this.doctorDao = doctorDao;
    }

    @Transactional
    @Override
    public Doctor save(DoctorDto doctorDto) {
        Doctor doctor = Doctor.builder()
                .doctorId(doctorDto.getDoctorId())
                .nombre(doctorDto.getNombre())
                .disponible(doctorDto.getDisponible())
                .build();
        return doctorDao.save(doctor);
    }

    @Transactional(readOnly = true)
    @Override
    public Doctor findById(Long Id) {
        return doctorDao.findById(Id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Doctor> findAll(){
        return StreamSupport.stream(doctorDao.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void delete(Doctor doctor) {
        doctorDao.delete(doctor);
    }
}