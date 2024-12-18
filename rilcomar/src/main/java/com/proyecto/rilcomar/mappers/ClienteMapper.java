package com.proyecto.rilcomar.mappers;

import com.proyecto.rilcomar.dtos.ClienteDto;
import com.proyecto.rilcomar.entities.Cliente;

public class ClienteMapper {

    public static ClienteDto buildDto (Cliente cliente) {
        return ClienteDto.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .telefono(cliente.getTelefono())
                .mail(cliente.getMail())
                .build();
    }

    public static Cliente buildEntity (ClienteDto cliente) {
        return Cliente.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .telefono(cliente.getTelefono())
                .mail(cliente.getMail())
                .build();
    }
}
