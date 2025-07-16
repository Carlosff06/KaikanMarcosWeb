package com.kaikan.infra.security;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kaikan.infra.models.AuthenticationRequest;
import com.kaikan.infra.models.AuthenticationResponse;
import com.kaikan.model.usuario.Usuario;
import com.kaikan.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UsuarioRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 TokenService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(Usuario request){
        Usuario user = new Usuario();
        user.setId(request.getId());
        user.setContrasenia(passwordEncoder.encode(request.getPassword()));

        user.setRol(request.getRol());

        user = repository.save(user);

        String token = jwtService.generarToken(user);

        return new AuthenticationResponse(token, user.getRol().name());
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        System.out.println(request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.contrasenia()
                )
        );

        Usuario user = repository.findByEmail(request.email()).orElseThrow();
        String token = jwtService.generarToken(user);
        System.out.println(new AuthenticationResponse(token, user.getRol().name()));
        return new AuthenticationResponse(token, user.getRol().name());
    }

}
