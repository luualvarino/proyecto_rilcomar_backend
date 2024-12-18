package com.proyecto.rilcomar.mappers;

import com.proyecto.rilcomar.dtos.PedidoDto;
import com.proyecto.rilcomar.entities.Pedido;
import com.proyecto.rilcomar.enums.EstadoEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PedidoMapper {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static PedidoDto buildDto(Pedido pedido) {
        return PedidoDto.builder()
                .id(pedido.getId())
                .estado(pedido.getEstado().name())
                .cliente(ClienteMapper.buildDto(pedido.getCliente()))
                .fechaCreacion(formatDate(pedido.getFechaCreacion()))
                .fechaEntrega(formatDate(pedido.getFechaEntrega()))
                .ultimaActualizacion(formatDate(pedido.getUltimaActualizacion()))
                .ubicacion(pedido.getUbicacion())
                .pallets(
                        pedido.getPallets()
                                .stream()
                                .map(pedidoPallet -> PalletMapper.buildDto(pedidoPallet.getPallet()))
                                .toList()
                )
                .build();
    }

    public static Pedido buildEntity(PedidoDto pedido){
        return Pedido.builder()
                .id(pedido.getId())
                .estado(EstadoEnum.valueOf(pedido.getEstado()))
                .cliente(ClienteMapper.buildEntity(pedido.getCliente()))
                .fechaCreacion(parseDate(pedido.getFechaCreacion()))
                .fechaEntrega(parseDate(pedido.getFechaEntrega()))
                .ultimaActualizacion(parseDate(pedido.getUltimaActualizacion()))
                .ubicacion(pedido.getUbicacion())
                .build();
    }

    public static Date parseDate(String dateString) {
        try {
            return dateString != null ? dateFormat.parse(dateString) : null;
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatDate(Date date) {
        return date != null ? dateFormat.format(date) : null;
    }
}
