package com.kaikan.model.empleado;

import com.kaikan.generics.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String nombre;
    private String apellido;
    @NotNull
    private String gmail;
    private String contrasenia;
    private String numero;
}
