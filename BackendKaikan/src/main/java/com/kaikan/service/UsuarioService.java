package com.kaikan.service;


import com.kaikan.generics.GenericService;
import com.kaikan.model.usuario.Usuario;
import com.kaikan.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends GenericService<Usuario, Long> {
    private final UsuarioRepository usuarioRepository;
    public UsuarioService (UsuarioRepository usuarioRepository){
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
    }
}
