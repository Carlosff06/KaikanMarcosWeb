package com.kaikan.model.detallePedido;

import com.kaikan.generics.BaseEntity;
import com.kaikan.model.pedido.Pedido;
import com.kaikan.model.plato.Plato;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Detalle_Pedido")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetallePedido extends BaseEntity {
    @JsonIgnore
    @ManyToOne
@JoinColumn(name = "id_pedido")
    @NotNull(message = "El pedido no puede ser nulo")
private Pedido pedido;
    @OneToOne
    @JoinColumn (name = "id_plato")
    private Plato plato;
    private Integer cantidad;
}
