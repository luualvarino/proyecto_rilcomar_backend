package com.proyecto.rilcomar.repos;

import com.proyecto.rilcomar.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, String> {


    List<Usuario> findByClienteId(int idCliente);
}