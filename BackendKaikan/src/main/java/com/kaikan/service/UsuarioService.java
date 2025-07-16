package com.kaikan.service;


import com.kaikan.generics.GenericService;
import com.kaikan.model.usuario.Usuario;
import com.kaikan.repository.UsuarioRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Service
@Validated
public class UsuarioService extends GenericService<Usuario, Long> {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private Validator validator;
    public UsuarioService (UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }





    public void registrarUsuario(@Valid Usuario usuario) {
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        String contraseñaHasheada = passwordEncoder.encode(usuario.getPassword());
        usuario.setContrasenia(contraseñaHasheada);
        usuarioRepository.save(usuario);
    }

}
