package com.proyecto.rilcomar.repos;

import com.proyecto.rilcomar.entities.Pedido;
import com.proyecto.rilcomar.enums.EstadoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Query("""
            SELECT p
            FROM Pedido p
            WHERE (:estado IS NULL OR p.estado = :estado)
            """)
    List<Pedido> findAllByEstado(EstadoEnum estado);
}
