package com.proyecto.rilcomar.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Pallet")
public class Pallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="estado")
    private String estado;

    @Column(name="tipo")
    private String tipo;

    @Column(name="esta_disponible")
    private boolean estaDisponible;

    @OneToMany(mappedBy = "pallet")
    private List<PedidoPallet> historial;
}
