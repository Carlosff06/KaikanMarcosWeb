package com.kaikan.model.usuario;


import com.kaikan.generics.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario extends BaseEntity {
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String contrasenia;
    private String email;
}
