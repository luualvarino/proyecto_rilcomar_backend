package com.proyecto.rilcomar.repos;

import com.proyecto.rilcomar.entities.PedidoPallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PedidoPalletRepository extends JpaRepository<PedidoPallet, Integer>, JpaSpecificationExecutor<PedidoPallet>{
}
