package com.kaikan.model.permiso;

import com.kaikan.generics.BaseEntity;
import com.kaikan.model.categoria.Categoria;
import com.kaikan.model.empleado.Empleado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;

@Entity
@Table(name = "permiso")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Permiso extends BaseEntity {
    private String permiso;
    @ManyToOne
    @JoinColumn (name = "id_empleado")
    private Empleado empleado;
    @OneToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

}
