package com.kaikan.service;

import com.kaikan.generics.GenericService;
import com.kaikan.model.plato.Plato;
import com.kaikan.repository.PlatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatoService extends GenericService<Plato, Long> {

    private final PlatoRepository platoRepository;

    public PlatoService(PlatoRepository platoRepository) {
        super(platoRepository);
        this.platoRepository = platoRepository;
    }

    public List<Plato> buscarPlatoPorCategoria(String categoria) {
        return platoRepository.buscarPlatoPorCategoria(categoria);
    }
}

