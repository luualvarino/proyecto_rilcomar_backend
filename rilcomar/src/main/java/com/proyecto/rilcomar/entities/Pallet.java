package com.proyecto.rilcomar.entities;

import com.proyecto.rilcomar.enums.EstadoPalletEnum;
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
    private EstadoPalletEnum estado;

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

    @ManyToOne
    @JoinColumn(name="pedido_actual", referencedColumnName = "id")
    private Pedido pedidoActual;

    @OneToMany(mappedBy = "pallet", cascade = CascadeType.ALL)
    private List<PedidoPallet> historial;
}
