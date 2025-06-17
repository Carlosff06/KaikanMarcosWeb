package com.kaikan.model.usuario;


import java.util.Collection;
import java.util.Collections;

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
    private String telefono;
    private String contrasenia;
    private String email;
    private String rol;
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return Collections.singletonList(new SimpleGrantedAuthority(rol));
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
