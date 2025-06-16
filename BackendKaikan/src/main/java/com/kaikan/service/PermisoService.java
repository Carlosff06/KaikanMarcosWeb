package com.kaikan.service;

import com.kaikan.generics.GenericRepository;
import com.kaikan.generics.GenericService;
import com.kaikan.model.permiso.Permiso;
import com.kaikan.repository.PermisoRepository;
import org.springframework.stereotype.Service;

@Service
public class PermisoService extends GenericService <Permiso, Long> {

    private final PermisoRepository permisoRepository;
    public PermisoService(PermisoRepository permisoRepository){
        super(permisoRepository);
        this.permisoRepository = permisoRepository;
    }

}
