package com.proyecto.rilcomar.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.rilcomar.enums.EstadoEnum;
import com.proyecto.rilcomar.enums.EstadoPalletEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name="estado", nullable = false)
    private EstadoEnum estado;

    @ManyToOne
    @JoinColumn(name="id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_entrega", nullable = false)
    private Date fechaEntrega;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm")
    @Temporal(TemporalType.DATE)
    @Column(name="ultima_actualizacion")
    private Date ultimaActualizacion;

    @Column(name="ubicacion")
    private String ubicacion;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoPallet> pallets;

    @Transient
    private List<Pallet> palletsAux;

    @PrePersist
    public void asignarEstadoCreado() {
        if (estado == null) {
            estado = EstadoEnum.Creado;
            fechaCreacion = new Date();
            ubicacion = "Deposito";
        }
        setUltimaActualizacion(new Date());
    }

    @PreRemove
    public void liberarPallets() {
        if (this.estado != EstadoEnum.Completado) {
            for (PedidoPallet pedidoPallet : pallets) {
                Pallet pallet = pedidoPallet.getPallet();
                if (pallet != null) {
                    pallet.setEstado(EstadoPalletEnum.Libre);
                }
            }
        }
    }
}
