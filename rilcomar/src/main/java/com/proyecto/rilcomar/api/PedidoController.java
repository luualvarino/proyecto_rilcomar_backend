package com.proyecto.rilcomar.api;

import com.proyecto.rilcomar.dtos.PedidoDto;
import com.proyecto.rilcomar.mappers.PedidoMapper;
import com.proyecto.rilcomar.services.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoDto> obtenerPedidos(@RequestParam(required = false) String estado){
        return pedidoService.obtenerPedidos(estado)
                .stream()
                .map(PedidoMapper :: buildDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> obtenerPedido(@PathVariable int id) {
        return ResponseEntity.ok(PedidoMapper.buildDto(pedidoService.obtenerPedido(id)));
    }

    @PostMapping
    public PedidoDto agregarPedido(@RequestBody PedidoDto pedido){
        return PedidoMapper.buildDto(pedidoService.agregarPedido(PedidoMapper.buildEntity(pedido)));
    }

    @PutMapping
    public PedidoDto editarPedido(@RequestBody PedidoDto pedido){
        return PedidoMapper.buildDto(pedidoService.editarPedido(PedidoMapper.buildEntity(pedido)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPedido(@PathVariable int id){
        pedidoService.eliminarPedido(id);
    }

    @GetMapping("/cliente")
    public List<PedidoDto> obtenerPedidosXCliente(@RequestParam(name = "clienteId") Integer clienteId){


        if (clienteId == null) {
            throw new IllegalArgumentException("El clienteId es obligatorio.");
        }

        return pedidoService.obtenerPedidosXCliente(clienteId)
                .stream()
                .map(PedidoMapper :: buildDto)
                .toList();
    }
}
