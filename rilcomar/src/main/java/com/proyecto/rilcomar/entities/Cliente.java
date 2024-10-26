package com.proyecto.rilcomar.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Cliente", schema="RILCOMAR")
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="clienteID")
    private int id;

    @Column(name="clienteNombre")
    private String nombre;

    @Column(name="clienteTelefono")
    private String telefono;

    @Column(name="clienteMail")
    private String email;
}
