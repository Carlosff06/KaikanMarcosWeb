package com.kaikan.repository;

import java.util.Optional;

import com.kaikan.generics.GenericRepository;
import com.kaikan.model.plato.Plato;
import com.kaikan.model.usuario.Usuario;

public interface UsuarioRepository extends GenericRepository <Usuario, Long>  {

    Optional<Usuario> findByEmail(String email);

}
