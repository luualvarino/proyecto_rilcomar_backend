package com.proyecto.rilcomar.services;

import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.enums.EstadoPalletEnum;
import com.proyecto.rilcomar.enums.MaterialEnum;
import com.proyecto.rilcomar.repos.PalletRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PalletService {
    @Autowired
    PalletRepository palletRepository;

    public List<Pallet> obtenerPallets(String estado, String tipo, String formato) {
        MaterialEnum materialEnum = tipo != null ? MaterialEnum.valueOf(tipo) : null;
        EstadoPalletEnum estadoPalletEnum = estado != null ? EstadoPalletEnum.valueOf(estado) : null;

        return palletRepository.findAllByEstadoAndTipoAndFormato(estadoPalletEnum, materialEnum, formato);
    }

    public List<Pallet> obtenerPalletsPorPedido(int pedidoId) {
        return palletRepository.findAllByPedido(pedidoId);
    }

    public Optional<Pallet> obtenerPallet(int id) { return palletRepository.findById(id); }

    public Pallet agregarPallet(Pallet pallet){
        pallet.setEstado(EstadoPalletEnum.Libre);
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
}
