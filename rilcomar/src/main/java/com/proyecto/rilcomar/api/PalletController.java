package com.proyecto.rilcomar.api;


import com.proyecto.rilcomar.dtos.PalletDto;
import com.proyecto.rilcomar.mappers.PalletMapper;
import com.proyecto.rilcomar.services.PalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/pallets", produces = MediaType.APPLICATION_JSON_VALUE)
public class PalletController {

    private final PalletService palletService;

    public PalletController(PalletService palletService) {
        this.palletService = palletService;
    }

    @GetMapping
    public List<PalletDto> obtenerPallets(
            @RequestParam("estado") String estado,
            @RequestParam("tipo") String tipo,
            @RequestParam("formato") String formato
    ) {
        if (estado.isEmpty() && tipo.isEmpty() && formato.isEmpty()) {
            return palletService.obtenerPallets()
                    .stream()
                    .map(PalletMapper::buildDto)
                    .toList();
        } else {
            return palletService.obtenerPalletsFiltrados(estado, tipo, formato)
                    .stream()
                    .map(PalletMapper::buildDto)
                    .toList();
        }
    }

    @GetMapping("/pedido/{pedidoId}")
    public List<PalletDto> obtenerPalletsPorPedido(@PathVariable int pedidoId) {
        return palletService.obtenerPalletsPorPedido(pedidoId)
                .stream()
                .map(PalletMapper :: buildDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PalletDto> obtenerPallet(@PathVariable int id) {
        return palletService.obtenerPallet(id)
                .map(pallet -> ResponseEntity.ok(PalletMapper.buildDto(pallet)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PalletDto agregarPallet(@RequestBody PalletDto pallet) {
        return PalletMapper.buildDto(palletService.agregarPallet(PalletMapper.buildEntity(pallet)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPallet(@PathVariable int id) {
        palletService.eliminarPallet(id);
    }
}
