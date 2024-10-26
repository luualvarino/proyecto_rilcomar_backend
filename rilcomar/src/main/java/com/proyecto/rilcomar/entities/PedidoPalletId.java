package com.proyecto.rilcomar.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PedidoPalletId {
    @Column(name = "pedidoID")
    private int pedidoId;

    @Column(name = "palletID")
    private int palletId;
}
