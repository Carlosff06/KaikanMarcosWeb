package com.kaikan.service;

import com.kaikan.generics.GenericService;
import com.kaikan.model.empleado.Empleado;
import com.kaikan.model.plato.Plato;
import com.kaikan.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService extends GenericService <Empleado, Long> {
    private final EmpleadoRepository empleadoRepository;
    public EmpleadoService (EmpleadoRepository empleadoRepository){
        super(empleadoRepository);
        this.empleadoRepository = empleadoRepository;
    }
}
