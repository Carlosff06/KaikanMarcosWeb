package com.kaikan.model.detallePedido;

import com.kaikan.generics.BaseEntity;
import com.kaikan.model.pedido.Pedido;
import com.kaikan.model.plato.Plato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Detalle_Pedido")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetallePedido extends BaseEntity {
    @ManyToOne
    @JoinColumn (name = "id_pedido")
    private Pedido pedido;
    @OneToOne
    @JoinColumn (name = "id_plato")
    private Plato plato;
    private Integer cantidad;
}
