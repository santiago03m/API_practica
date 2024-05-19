package org.practica.miprimerapirest.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name="Pacientes")

public class Paciente implements Serializable {
    @Id
    @Column(name="paciente_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pacienteId;

    @Column(name="nombre")
    private String nombre;

    @Column(name="edad")
    private int edad;

    @Column(name="tipo_sangre")
    private String tipoSangre;

    @Column(name="ultima_cita")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ultimaCita;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;
}