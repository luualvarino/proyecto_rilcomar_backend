package com.proyecto.rilcomar.api;


import com.proyecto.rilcomar.dtos.PalletDto;
import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.enums.EstadoPalletEnum;
import com.proyecto.rilcomar.mappers.PalletMapper;
import com.proyecto.rilcomar.services.PalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pallets", produces = MediaType.APPLICATION_JSON_VALUE)
public class PalletController {

    private final PalletService palletService;

    public PalletController(PalletService palletService) {
        this.palletService = palletService;
    }

    @GetMapping
    public List<PalletDto> obtenerPallets(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String formato
    ) {
        return palletService.obtenerPallets(estado, tipo, formato)
                .stream()
                .map(PalletMapper::buildDto)
                .toList();
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
                .map(pallet -> {
                    PalletDto palletDto = PalletMapper.buildDto(pallet);
                    palletDto.setQrCodeUrl(pallet.getQrCodeUrl());
                    return ResponseEntity.ok(palletDto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/countByEstado")
    public Map<String, Long> countPalletsByEstado() {
        Map<String, Long> result = new HashMap<>();
        result.put("Libre", palletService.countByEstado(EstadoPalletEnum.Libre));
        result.put("Ocupado", palletService.countByEstado(EstadoPalletEnum.Ocupado));
        return result;
    }

    @PostMapping
    public List<PalletDto> agregarPallet(@RequestBody PalletDto pallet, @RequestParam(required = false) Integer cantidad) {
        return palletService.agregarPallets(PalletMapper.buildEntity(pallet), cantidad)
                .stream()
                .map(PalletMapper::buildDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPallet(@PathVariable int id) {
        palletService.eliminarPallet(id);
    }

    @GetMapping("/{id}/qrcode")
    public ResponseEntity<byte[]> obtenerPalletQRCode(@PathVariable int id) {
        Optional<Pallet> palletOptional = palletService.obtenerPallet(id);

        if (palletOptional.isPresent() && palletOptional.get().getQrCode() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(palletOptional.get().getQrCode());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
