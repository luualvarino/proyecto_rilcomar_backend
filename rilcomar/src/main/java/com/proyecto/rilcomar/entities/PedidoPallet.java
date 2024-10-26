package com.proyecto.rilcomar.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Pedido_Pallet", schema ="RILCOMAR")
public class PedidoPallet {

    @EmbeddedId
    private PedidoPalletId id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedidoID")
    private Pedido pedido;

    @ManyToOne
    @MapsId("palletId")
    @JoinColumn(name = "palletID")
    private Pallet pallet;
}
