package com.proyecto.rilcomar.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PedidoPalletId {
    @Column(name = "pedido_id")
    private int pedidoId;

    @Column(name = "pallet_id")
    private int palletId;
}
