package com.proyecto.rilcomar.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Pallet", schema="RILCOMAR")
public class Pallet {
    @Id
    @GeneratedValue
    @Column(name="palletID")
    private int id;

    @Column(name="palletEstado")
    private String estado;

    @Column(name="palletTipo")
    private String tipo;

    @Column(name="palletDisponible")
    private boolean estaDisponible;

    @OneToMany(mappedBy = "pallet")
    private List<PedidoPallet> historial;
}
