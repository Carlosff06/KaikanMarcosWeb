package com.kaikan.model.pedido;


import com.kaikan.generics.BaseEntity;
import com.kaikan.model.detallePedido.DetallePedido;
import com.kaikan.model.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pedido extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @NotNull
    private Usuario usuario;
    @NotNull
    private LocalDateTime fecha_pedido;
    private LocalDateTime fecha_pago;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @NotEmpty
    private List<DetallePedido> detallesPedido;


}
