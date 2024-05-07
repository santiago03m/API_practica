package org.practica.miprimerapirest.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="Doctores")

public class Doctor implements Serializable {
    @Id
    @Column(name="DoctorId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    @Column(name="Nombre")
    private String nombre;
    @Column(name="Disponible")
    private int disponible;
}
