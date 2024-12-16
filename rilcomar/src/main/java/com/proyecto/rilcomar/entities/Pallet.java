package com.proyecto.rilcomar.entities;

import com.proyecto.rilcomar.dtos.PalletDto;
import com.proyecto.rilcomar.enums.MaterialEnum;
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
    @Column(name="id", nullable = false)
    private int id;

    @Column(name="estado", nullable = false)
    private String estado; // quizas deba ser un enum.

    @Enumerated(EnumType.STRING)
    @Column(name="tipo", nullable = false)
    private MaterialEnum tipo;

    @Column(name="esta_disponible", nullable = false)
    private boolean estaDisponible;

    @Column(name="formato", nullable = false)
    private String formato;

    @Column(name="peso")
    private Double peso ;

    @Column(name="observaciones")
    private String observaciones ; // Comentarios adicionales, estado fisico del pallet, opcional.

    @Column(name="ubicacion")
    private String ubicacion;

    @OneToMany(mappedBy = "pallet")
    private List<PedidoPallet> historial;


    public static Pallet build(PalletDto pallet){
        return Pallet.builder()
                .id(pallet.getId())
                .estado(pallet.getEstado())
                .tipo(MaterialEnum.valueOf(pallet.getTipo()))
                .estaDisponible(pallet.isEstaDisponible())
                .formato(pallet.getFormato())
                .peso(pallet.getPeso())
                .observaciones(pallet.getObservaciones())
                .ubicacion(pallet.getUbicacion())
                .build();
    }

}
