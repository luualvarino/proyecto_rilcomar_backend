package com.proyecto.rilcomar.services;
import java.util.List;
import com.proyecto.rilcomar.entities.Usuario;
import com.proyecto.rilcomar.repos.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario autenticarUsuario(Usuario c) {
        Usuario usuario = usuarioRepository.findById(c.getUsername()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getPassword().equals(c.getPassword())) {
            return usuario;
        } else {
            throw new RuntimeException("Contraseña incorrecta");
        }
    }

    public boolean esAdmin(Usuario usuario) {
        return usuario.isEsAdmin();
    }

    public Usuario agregarUsuario(Usuario usuario) {
        try{
            if (usuarioRepository.existsById(usuario.getUsername()))
                throw new RuntimeException("El nombre de usuario ya existe");
            else return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al guardar el usuario", e);
        }
    }

    public List<Usuario> obtenerUsuario() {
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
}
