package com.proyecto.rilcomar.repos;

import com.proyecto.rilcomar.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
