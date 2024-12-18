package com.proyecto.rilcomar.services;

import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.enums.EstadoEnum;
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

    public List<Pallet> obtenerPallets() { return palletRepository.findAll(); }

    public List<Pallet> obtenerPalletsFiltrados(String estado, String tipo, String formato) {
        EstadoEnum estadoEnum = !estado.isEmpty() ? EstadoEnum.valueOf(estado) : null;
        MaterialEnum materialEnum = !tipo.isEmpty() ? MaterialEnum.valueOf(tipo) : null;

        if (estadoEnum != null && materialEnum != null && !formato.isEmpty()) {
            return palletRepository.findAllByEstadoAndTipoAndFormato(estadoEnum, materialEnum, formato);
        } else if (estadoEnum != null && materialEnum != null) {
            return palletRepository.findAllByEstadoAndTipo(estadoEnum, materialEnum);
        } else if (estadoEnum != null && !formato.isEmpty()) {
            return palletRepository.findAllByEstadoAndFormato(estadoEnum, formato);
        } else if (estadoEnum != null) {
            return palletRepository.findAllByEstado(estadoEnum);
        } else if (materialEnum != null && !formato.isEmpty()) {
            return palletRepository.findAllByTipoAndFormato(materialEnum, formato);
        } else if (materialEnum != null) {
            return palletRepository.findAllByTipo(materialEnum);
        } else {
            return palletRepository.findAllByFormato(formato);
        }
    }

    public List<Pallet> obtenerPalletsPorPedido(int pedidoId) {
        return palletRepository.findAllByPedido(pedidoId);
    }

    public Optional<Pallet> obtenerPallet(int id) { return palletRepository.findById(id); }

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
}
