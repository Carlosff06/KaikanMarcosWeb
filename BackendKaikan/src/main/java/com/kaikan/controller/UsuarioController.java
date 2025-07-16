package com.kaikan.controller;


import com.kaikan.generics.GenericController;
import com.kaikan.model.usuario.Usuario;
import com.kaikan.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController extends GenericController <Usuario, Long> {
    private  final UsuarioService usuarioSerivce;
    public UsuarioController(UsuarioService usuarioService){
        super(usuarioService);
        this.usuarioSerivce = usuarioService;
    }

    @Override
    @PostMapping("/crear")
    public Usuario guardar(@RequestBody @Valid Usuario usuario){
        usuarioSerivce.registrarUsuario(usuario);
        return usuario;
    }
}
