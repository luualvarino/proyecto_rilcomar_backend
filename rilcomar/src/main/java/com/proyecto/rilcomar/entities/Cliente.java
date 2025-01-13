package com.proyecto.rilcomar.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Cliente")
@Builder
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

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
}
