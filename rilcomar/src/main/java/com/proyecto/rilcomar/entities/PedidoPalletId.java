package com.proyecto.rilcomar.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PedidoPalletId {
    @Column(name = "pedido_id")
    private int pedidoId;

    @Column(name = "pallet_id")
    private int palletId;
}
