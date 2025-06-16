package com.kaikan.controller;

import com.kaikan.generics.GenericController;
import com.kaikan.generics.GenericService;
import com.kaikan.model.permiso.Permiso;
import com.kaikan.service.PermisoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permiso")
public class PermisoController extends GenericController <Permiso, Long> {
    private final PermisoService permisoService;
    public PermisoController( PermisoService permisoService) {
        super(permisoService);
        this.permisoService = permisoService;
    }
}

