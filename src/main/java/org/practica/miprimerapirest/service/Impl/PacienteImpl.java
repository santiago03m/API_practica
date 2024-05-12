package org.practica.miprimerapirest.service.Impl;

import org.practica.miprimerapirest.model.dao.PacienteDao;
import org.practica.miprimerapirest.model.dto.PacienteDto;
import org.practica.miprimerapirest.model.entity.Paciente;
import org.practica.miprimerapirest.service.IPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteImpl implements IPaciente {

    private final PacienteDao pacienteDao;

    @Autowired
    public PacienteImpl(PacienteDao pacienteDao) {
        this.pacienteDao = pacienteDao;
    }

    @Transactional
    @Override
    public Paciente save(PacienteDto pacienteDto) {
        Paciente paciente = Paciente.builder()
                .pacienteId(pacienteDto.getPacienteId())
                .nombre(pacienteDto.getNombre())
                .edad(pacienteDto.getEdad())
                .tipoSangre(pacienteDto.getTipoSangre())
                .ultimaCita(pacienteDto.getUltimaCita())
                .doctor(pacienteDto.getDoctor())
                .build();
        return pacienteDao.save(paciente);
    }

    @Transactional(readOnly = true)
    @Override
    public Paciente findById(Long Id) {
        return pacienteDao.findById(Id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Paciente paciente) {
        pacienteDao.delete(paciente);
    }
}