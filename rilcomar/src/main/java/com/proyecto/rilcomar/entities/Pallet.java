package com.proyecto.rilcomar.entities;

import com.proyecto.rilcomar.dtos.PalletDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="Pallet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="estado")
    private String estado;

    @Column(name="tipo")
    private String tipo;

    @Column(name="esta_disponible")
    private boolean estaDisponible;

    @OneToMany(mappedBy = "pallet")
    private List<PedidoPallet> historial;


    public static Pallet build(PalletDto pallet){
        return Pallet.builder()
                .id(pallet.getId())
                .estado(pallet.getEstado())
                .tipo(pallet.getTipo())
                .estaDisponible(pallet.isEstaDisponible())
                .build();
    }
}
