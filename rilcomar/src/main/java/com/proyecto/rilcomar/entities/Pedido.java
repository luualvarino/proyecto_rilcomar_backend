package com.proyecto.rilcomar.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Pedido")
public class Pedido {
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name="id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @Column(name="fecha_creacion")
    private Date fechaCreacion;

    @Column(name="fecha_entrega")
    private Date fechaEntrega;

    @Column(name="ultima_actualizacion")
    private Date ultimaActualizacion;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoPallet> pallets;
}
