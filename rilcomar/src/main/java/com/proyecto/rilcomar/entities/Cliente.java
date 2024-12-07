package com.proyecto.rilcomar.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Cliente")
public class Cliente {
    @Id
    @GeneratedValue()
    @Column(name="id")
    private int id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="telefono")
    private String telefono;

    @Column(name="mail")
    private String mail;
}
