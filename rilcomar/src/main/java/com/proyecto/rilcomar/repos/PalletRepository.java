package com.proyecto.rilcomar.repos;

import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.enums.EstadoPalletEnum;
import com.proyecto.rilcomar.enums.MaterialEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PalletRepository extends JpaRepository<Pallet, Integer>, JpaSpecificationExecutor<Pallet> {
    @Query("""
            SELECT p
            FROM Pallet p
            WHERE (:estado IS NULL OR p.estado = :estado)
            AND (:tipo IS NULL OR p.tipo = :tipo)
            AND (:formato IS NULL OR p.formato = :formato)
            """)
    List<Pallet> findAllByEstadoAndTipoAndFormato(EstadoPalletEnum estado, MaterialEnum tipo, String formato);

    @Query("""
            SELECT p
            FROM Pallet p
            JOIN PedidoPallet pp ON pp.pallet.id = p.id
            WHERE pp.pedido.id = :pedidoId
            """)
    List<Pallet> findAllByPedido(int pedidoId);

    @Query("SELECT COUNT(p) FROM Pallet p WHERE p.estado = :estado")
    long countByEstado(EstadoPalletEnum estado);
}
