package com.proyecto.rilcomar.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario", schema = "RILCOMAR")
public class Usuario {
    @Id
    @Column(name="usuarioUser")
    private String username;

    @Column(name="usuarioClave")
    private String password;

    @Column(name="usuarioEsAdmin")
    private boolean esAdmin;

    @Column(name = "usuarioIDCliente")
    private Cliente cliente;
}
