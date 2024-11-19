package com.proyecto.rilcomar.entities;

import com.proyecto.rilcomar.dtos.PalletDto;
import com.proyecto.rilcomar.dtos.UsuarioDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="es_admin")
    private boolean esAdmin;

    @ManyToOne
    @JoinColumn(name="id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    public static Usuario build(UsuarioDto usuarioDto){
        return Usuario.builder()
                .username(usuarioDto.getUsername())
                .password(usuarioDto.getPassword())
                .esAdmin(usuarioDto.isEsAdmin())
                .build();
    }
}
