package com.proyecto.rilcomar.api;


import com.proyecto.rilcomar.dtos.PalletDto;
import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.services.PalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/pallets", produces = MediaType.APPLICATION_JSON_VALUE)
public class PalletController {

    @Autowired
    PalletService palletService;


    @PostMapping
    public PalletDto agregarPallet(@RequestBody PalletDto pallet){
        return PalletDto.build(palletService.agregarPallet(Pallet.build(pallet)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPallet(@PathVariable int id){
        palletService.eliminarPallet(id);
    }

    @GetMapping
    public List<PalletDto> obtenerPallets(){
        return palletService.obtenerPallets()
                .stream()
                .map(PalletDto :: build)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PalletDto> obtenerPallet(@PathVariable int id) {
        return palletService.obtenerPallet(id)
                .map(pallet -> ResponseEntity.ok(PalletDto.build(pallet)))
                .orElse(ResponseEntity.notFound().build());
    }
}
