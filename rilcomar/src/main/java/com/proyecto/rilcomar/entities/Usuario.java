package com.proyecto.rilcomar.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="es_admin")
    private boolean esAdmin;

    @ManyToOne
    @JoinColumn(name="id_cliente", referencedColumnName = "id")
    private Cliente cliente;
}
