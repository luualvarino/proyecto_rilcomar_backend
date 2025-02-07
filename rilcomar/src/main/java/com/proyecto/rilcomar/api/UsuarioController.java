package com.proyecto.rilcomar.api;

import java.util.List;

import com.proyecto.rilcomar.dtos.UsuarioDto;
import com.proyecto.rilcomar.entities.Usuario;
import com.proyecto.rilcomar.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController{
    @Autowired
    UsuarioService usuarioService;
    @PostMapping
    public UsuarioDto agregarUsuario(@RequestBody UsuarioDto usuario) {
        Integer idCliente = usuario.getCliente() != null ? usuario.getCliente().getId() : null;
        return UsuarioDto.build(usuarioService.agregarUsuario(Usuario.build(usuario), idCliente));
    }

    @PostMapping("/login")
    public UsuarioDto autenticarUsuario(@RequestBody UsuarioDto usuarioDto) {
        return UsuarioDto.build(usuarioService.autenticarUsuario(Usuario.build(usuarioDto)));
    }
    @GetMapping
    public List<UsuarioDto> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios()
                .stream()
                .map(UsuarioDto :: build)
                .toList();
    }

    @GetMapping("/{idCliente}")//quizas no esta bueno mostrar el id del cliente
    public List<UsuarioDto> obtenerUsuariosCliente(@PathVariable int idCliente) {
        return usuarioService.obtenerUsuariosPorCliente(idCliente)
                .stream()
                .map(UsuarioDto::build)
                .toList();

    }

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable String username){
        usuarioService.eliminarUsuario(username);
    }

    //eliminar usuarios asociados a un cliente segun el id del cliente

}
