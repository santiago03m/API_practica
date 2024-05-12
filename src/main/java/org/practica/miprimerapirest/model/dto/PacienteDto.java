package org.practica.miprimerapirest.model.dto;

import lombok.*;
import org.practica.miprimerapirest.model.entity.Doctor;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class PacienteDto implements Serializable {
    private Long pacienteId;

    private String nombre;

    private int edad;

    private String tipoSangre;

    private Date ultimaCita;

    private Doctor doctor;
}