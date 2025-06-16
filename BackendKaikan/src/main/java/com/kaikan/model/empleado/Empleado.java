package com.kaikan.model.empleado;

import com.kaikan.generics.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empleado")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Empleado extends BaseEntity {
    private String nombre;
    private String apellido;
    private String gmail;
    private String contrasenia;
    private String numero;
}
