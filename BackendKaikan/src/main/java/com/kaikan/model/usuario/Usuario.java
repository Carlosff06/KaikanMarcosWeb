package com.kaikan.model.usuario;


import java.util.Collection;
import java.util.Collections;

import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Usuario extends BaseEntity implements UserDetails {
    private String nombre;
    private String apellido;
    private String direccion;
    @Column(name = "telefono")
    @Size(min = 9, max = 9, message = "El número de teléfono debe tener exactamente 9 dígitos")
    @Pattern(regexp = "^9\\d{8}$", message = "El número debe comenzar con 9 y tener 9 dígitos")
    private String telefono;
    @NotNull
    private String contrasenia;
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El formato del correo electrónico no es válido")
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + rol));

    }
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return contrasenia;
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return email;
    }


}
