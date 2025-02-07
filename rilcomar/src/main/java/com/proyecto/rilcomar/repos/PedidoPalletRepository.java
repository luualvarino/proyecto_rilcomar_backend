package com.proyecto.rilcomar.repos;

import com.proyecto.rilcomar.entities.PedidoPallet;
import com.proyecto.rilcomar.entities.PedidoPalletId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidoPalletRepository extends JpaRepository<PedidoPallet, PedidoPalletId>, JpaSpecificationExecutor<PedidoPallet>{

}
