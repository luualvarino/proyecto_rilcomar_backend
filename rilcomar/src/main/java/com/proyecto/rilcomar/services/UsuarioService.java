package com.proyecto.rilcomar.services;
import java.util.List;
import com.proyecto.rilcomar.entities.Usuario;
import com.proyecto.rilcomar.repos.UsuarioRepository;
import com.proyecto.rilcomar.repos.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public Usuario autenticarUsuario(Usuario usuario) {
        Usuario usuarioEncontrado = usuarioRepository.findById(usuario.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuarioEncontrado.getPassword().equals(usuario.getPassword())) {
            return usuarioEncontrado;
        } else {
            throw new RuntimeException("Contraseña incorrecta");
        }
    }


    public Usuario agregarUsuario(Usuario usuario, Integer idCliente) {
        try {
            if (usuarioRepository.existsById(usuario.getUsername())) {
                throw new RuntimeException("El nombre de usuario ya existe");
            }

            if (!usuario.isEsAdmin() && idCliente != null) {
                usuario.setCliente(clienteRepository.findById(idCliente)
                        .orElseThrow(() -> new RuntimeException("Cliente no encontrado")));
            }

            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al guardar el usuario", e);
        }
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public void eliminarUsuario(String username) {
        try{
            if (usuarioRepository.existsById(username))
                usuarioRepository.deleteById(username);
            else throw new EntityNotFoundException("Usuario " + username + " no encontrado");
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al guardar el usuario", e);
        }
    }


    public List<Usuario> obtenerUsuariosPorCliente(int idCliente) {
        List<Usuario> usuarios = usuarioRepository.findByClienteId(idCliente);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("No se encontraron usuarios para el cliente.");
        }
        return usuarios;
    }

}
