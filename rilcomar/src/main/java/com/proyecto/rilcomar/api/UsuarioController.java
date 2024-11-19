package com.proyecto.rilcomar.api;

import java.util.List;

import com.proyecto.rilcomar.dtos.PalletDto;
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
    public UsuarioDto agregarUsuario(@RequestBody UsuarioDto usuario){
        return UsuarioDto.build(usuarioService.agregarUsuario(Usuario.build(usuario)));
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

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable String username){
        usuarioService.eliminarUsuario(username);
    }
}
