package com.kaikan.model.plato;

import com.kaikan.generics.BaseEntity;
import com.kaikan.model.categoria.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "plato", schema = "public")
@AllArgsConstructor
@NoArgsConstructor

@Data

public class Plato extends BaseEntity {
    private String nombre;
    private String descripcion;
    private Double precio;
    private String img_url;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "id_categoria", nullable = false)
    private Categoria categoria;
}
