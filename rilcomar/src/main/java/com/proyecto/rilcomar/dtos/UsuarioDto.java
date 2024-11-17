package com.proyecto.rilcomar.dtos;

import com.proyecto.rilcomar.entities.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UsuarioDto {
    private String username;
    private String password;
    private boolean esAdmin;

    public static UsuarioDto build(Usuario usuario){
        return UsuarioDto.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .esAdmin(usuario.isEsAdmin())
                .build();
    }
}
