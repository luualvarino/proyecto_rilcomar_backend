package com.proyecto.rilcomar.repos;

import com.proyecto.rilcomar.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
