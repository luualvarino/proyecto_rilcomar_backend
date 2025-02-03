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

    @Query("""
            SELECT pe
            FROM Pedido pe
            WHERE pe.cliente.id = :clienteId
            """)
    List<Pedido> findAllByCliente(int clienteId);

    @Query("""
            SELECT p
            FROM Pedido p
            WHERE p.cliente.id = :clienteId AND (:estado IS NULL OR p.estado = :estado)
            """)
    List<Pedido> findAllByClienteEstado(int clienteId, EstadoEnum estado);
}
