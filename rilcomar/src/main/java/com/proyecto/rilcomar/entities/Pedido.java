package com.proyecto.rilcomar.entities;

import com.proyecto.rilcomar.enums.EstadoEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Pedido")
@Getter
@Setter
@Builder
public class Pedido {
    @Id
    @GeneratedValue
    @Column(name="id", nullable = false)
    private int id;

    @Column(name="estado", nullable = false)
    private EstadoEnum estado;

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
