package com.proyecto.rilcomar.repos;

import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.enums.EstadoEnum;
import com.proyecto.rilcomar.enums.MaterialEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PalletRepository extends JpaRepository<Pallet, Integer>, JpaSpecificationExecutor<Pallet> {
    List<Pallet> findAllByEstado(EstadoEnum estado);

    List<Pallet> findAllByTipo(MaterialEnum tipo);

    List<Pallet> findAllByFormato(String formato);

    List<Pallet> findAllByEstadoAndTipo(EstadoEnum estado, MaterialEnum tipo);

    List<Pallet> findAllByEstadoAndFormato(EstadoEnum estado, String formato);

    List<Pallet> findAllByTipoAndFormato(MaterialEnum tipo, String formato);

    List<Pallet> findAllByEstadoAndTipoAndFormato(EstadoEnum estado, MaterialEnum tipo, String formato);

    @Query("""
            SELECT p
            FROM Pallet p
            JOIN PedidoPallet pp ON pp.pallet.id = p.id
            WHERE pp.pedido.id = :pedidoId
            """)
    List<Pallet> findAllByPedido(int pedidoId);
}
