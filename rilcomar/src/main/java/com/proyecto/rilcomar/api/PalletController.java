package com.proyecto.rilcomar.api;

import com.proyecto.rilcomar.dtos.PalletDto;
import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.services.PalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping
    public List<PalletDto> obtenerPallets(){
        return palletService.obtenerPallets()
                .stream()
                .map(PalletDto :: build)
                .toList();
    }
}
