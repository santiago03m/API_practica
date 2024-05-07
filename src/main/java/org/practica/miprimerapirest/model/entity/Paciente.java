package org.practica.miprimerapirest.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="Pacientes")

public class Paciente implements Serializable {
    @Id
    @Column(name="PacienteId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pacienteId;

    @Column(name="Nombre")
    private String nombre;

    @Column(name="Edad")
    private int edad;

    @Column(name="TipoSangre")
    private String tipoSangre;

    @Column(name="UltimaCita")
    private Date ultimaCita;

    @ManyToOne
    @JoinColumn(name="DoctorId")
    private Doctor doctor;
}