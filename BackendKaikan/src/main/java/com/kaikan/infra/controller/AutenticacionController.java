package com.kaikan.infra.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.kaikan.infra.models.AuthenticationRequest;
import com.kaikan.infra.models.AuthenticationResponse;
import com.kaikan.infra.security.AuthenticationService;
import com.kaikan.model.usuario.Usuario;

@RestController
@RequestMapping("/api")
public class AutenticacionController {


    private final AuthenticationService authenticationService;

    public AutenticacionController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Usuario request
    ){
       return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody AuthenticationRequest request){

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
