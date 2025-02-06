package com.proyecto.rilcomar.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PedidoPalletId implements Serializable {
    @Column(name = "pedido_id")
    private int pedidoId;

    @Column(name = "pallet_id")
    private int palletId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoPalletId that = (PedidoPalletId) o;
        return pedidoId == that.pedidoId && palletId == that.palletId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, palletId);
    }
}
