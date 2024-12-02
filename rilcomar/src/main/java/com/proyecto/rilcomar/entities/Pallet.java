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
    private String estado; // quizas deba ser un enum.

    @Column(name="tipo")
    private String tipo;

    @Column(name="esta_disponible")
    private boolean estaDisponible;

    @Column(name="formato" , nullable = true)
    private String formato; //EuroPallet, Mercosur, quizas deba ser un enum.

    @Column(name="peso", nullable = true)
    private Double peso ; // opcional.

    @Column(name="dimensiones", nullable = true)
    private String dimensiones ; // Ejemplo: "120x80x15 cm", opcional.

    @Column(name="observaciones", nullable = true)
    private String observaciones ; // Comentarios adicionales, estado fisico del pallet, opcional.

    @OneToMany(mappedBy = "pallet")
    private List<PedidoPallet> historial;


    public static Pallet build(PalletDto pallet){
        return Pallet.builder()
                .id(pallet.getId())
                .estado(pallet.getEstado())
                .tipo(pallet.getTipo())
                .estaDisponible(pallet.isEstaDisponible())
                .formato(pallet.getFormato()) //Nuevo campo.
                .peso(pallet.getPeso()) // Nuevo campo.
                .dimensiones(pallet.getDimensiones()) // Nuevo campo.
                .observaciones(pallet.getObservaciones()) // Nuevo campo.
                .build();
    }

}
