package com.proyecto.rilcomar.api;

import com.proyecto.rilcomar.dtos.PalletDto;
import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.services.PalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pallets", produces = MediaType.APPLICATION_JSON_VALUE)
public class PalletController {

    @Autowired
    PalletService palletService;

    @PostMapping
    public PalletDto agregarPallet(@RequestBody PalletDto pallet){
       return PalletDto.build(palletService.agregarPallet(Pallet.build(pallet)));
    }
}
