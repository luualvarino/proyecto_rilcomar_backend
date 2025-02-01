package com.proyecto.rilcomar.services;

import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.enums.EstadoPalletEnum;
import com.proyecto.rilcomar.enums.MaterialEnum;
import com.proyecto.rilcomar.repos.PalletRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Pallet> agregarPallets(Pallet pallet, int cantidad){
        List<Pallet> palletsCreados = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {
            Pallet nuevoPallet = new Pallet();

            nuevoPallet.setTipo(pallet.getTipo());
            nuevoPallet.setPeso(pallet.getPeso());
            nuevoPallet.setFormato(pallet.getFormato());
            nuevoPallet.setObservaciones(pallet.getObservaciones());
            nuevoPallet.setEstado(EstadoPalletEnum.Libre);

            palletsCreados.add(palletRepository.save(nuevoPallet));
        }

        return palletsCreados;
    }

    public void eliminarPallet(int id) {
        try {
            Pallet pallet = palletRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Pallet " + id + " no encontrado"));

            if (pallet.getEstado() == EstadoPalletEnum.Ocupado) {
                throw new IllegalStateException("No se puede eliminar un pallet con estado 'Ocupado'.");
            }

            palletRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al eliminar el pallet", e);
        }
    }

    public Long countByEstado(EstadoPalletEnum estadoPalletEnum) {
        return palletRepository.countByEstado(estadoPalletEnum);
    }
}
