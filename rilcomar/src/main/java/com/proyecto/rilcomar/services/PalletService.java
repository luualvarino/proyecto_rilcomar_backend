package com.proyecto.rilcomar.services;

import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.repos.PalletRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalletService {
    @Autowired
    PalletRepository palletRepository;

    public Pallet agregarPallet(Pallet pallet){
        return palletRepository.save(pallet);
    }

    public void eliminarPallet(int id) {
        try {
            if (palletRepository.existsById(id))
                palletRepository.deleteById(id);
            else
                throw new EntityNotFoundException("Pallet " + id + " no encontrado");
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al eliminar el pallet", e);
        }
    }

    public List<Pallet> obtenerPallets() { return palletRepository.findAll(); }

}
