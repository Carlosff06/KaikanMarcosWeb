package com.kaikan.model.pedido;


import com.kaikan.generics.BaseEntity;
import com.kaikan.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pedido extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    private LocalDateTime fecha_pedido;
    private LocalDateTime fecha_pago;
    @Enumerated(EnumType.STRING)
    private Estado estado;

}
