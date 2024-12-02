package com.proyecto.rilcomar.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Pedido")
public class Pedido {
    @Id
    @GeneratedValue
    @Column(name="id", nullable = false)
    private int id;

    @Column(name="estado", nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name="id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @Column(name="fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name="fecha_entrega", nullable = false)
    private Date fechaEntrega;

    @Column(name="ultima_actualizacion")
    private Date ultimaActualizacion;

    @Column(name="ubicacion")
    private String ubicacion;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoPallet> pallets;
}
