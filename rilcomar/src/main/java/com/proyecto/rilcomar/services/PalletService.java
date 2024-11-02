package com.proyecto.rilcomar.services;

import com.proyecto.rilcomar.dtos.PalletDto;
import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.repos.PalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PalletService {
    @Autowired
    PalletRepository palletRepository;

    public Pallet agregarPallet(Pallet pallet){
        return palletRepository.save(pallet);
    }
}
