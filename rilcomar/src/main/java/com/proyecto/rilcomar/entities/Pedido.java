package com.proyecto.rilcomar.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Pedido", schema="RILCOMAR")
public class Pedido {
    @Id
    @GeneratedValue
    @Column(name="pedidoID")
    private int id;

    @Column(name="pedidoEstado")
    private String estado;

    @Column(name="pedidoIDCliente")
    private Cliente cliente;

    @Column(name="pedidoFechaCreacion")
    private Date fechaCreacion;

    @Column(name="pedidoFechaEntrega")
    private Date fechaEntrega;

    @Column(name="pedidoActualizacion")
    private Date ultimaActualizacion;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoPallet> pallets;
}
