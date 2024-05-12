package org.practica.miprimerapirest.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@ToString
@Builder
public class DoctorDto implements Serializable {
    private Long doctorId;

    private String nombre;

    private int disponible;
}