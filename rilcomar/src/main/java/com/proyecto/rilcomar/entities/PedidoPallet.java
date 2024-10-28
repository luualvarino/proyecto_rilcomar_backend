package com.proyecto.rilcomar.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Pedido_Pallet")
public class PedidoPallet {

    @EmbeddedId
    private PedidoPalletId id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @MapsId("palletId")
    @JoinColumn(name = "pallet_id")
    private Pallet pallet;
}
