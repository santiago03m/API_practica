package org.practica.miprimerapirest.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto implements Serializable {
    private Long doctorId;

    private String nombre;

    private int disponible;
}