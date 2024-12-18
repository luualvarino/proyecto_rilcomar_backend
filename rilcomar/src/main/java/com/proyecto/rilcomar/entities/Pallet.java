package com.proyecto.rilcomar.entities;

import com.proyecto.rilcomar.enums.EstadoEnum;
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

    @Enumerated(EnumType.STRING)
    @Column(name="estado", nullable = false)
    private EstadoEnum estado;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo", nullable = false)
    private MaterialEnum tipo;

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
}
