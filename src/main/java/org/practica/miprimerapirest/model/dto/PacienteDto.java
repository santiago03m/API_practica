package org.practica.miprimerapirest.model.dto;

import lombok.*;
import org.practica.miprimerapirest.model.entity.Doctor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDto implements Serializable {
    private Long pacienteId;

    private String nombre;

    private int edad;

    private String tipoSangre;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ultimaCita;

    private Doctor doctor;
}