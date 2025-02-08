package com.proyecto.rilcomar.api;

import com.proyecto.rilcomar.dtos.ClienteDto;
import com.proyecto.rilcomar.mappers.ClienteMapper;
import com.proyecto.rilcomar.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDto> obtenerClientes(){
        return clienteService.obtenerClientes()
                .stream()
                .map(ClienteMapper:: buildDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerCliente(@PathVariable int id) {
        return clienteService.obtenerCliente(id)
                .map(cliente -> ResponseEntity.ok(ClienteMapper.buildDto(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClienteDto agregarCliente(@RequestBody ClienteDto cliente){
        return ClienteMapper.buildDto(clienteService.agregarCliente(ClienteMapper.buildEntity(cliente)));
    }

    @PutMapping("/{id}")
    public ClienteDto editarCliente(@PathVariable int id, @RequestBody ClienteDto cliente){
        return ClienteMapper.buildDto(clienteService.editarCliente(ClienteMapper.buildEntity(cliente)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCliente(@PathVariable int id){
        clienteService.eliminarCliente(id);
    }
}
