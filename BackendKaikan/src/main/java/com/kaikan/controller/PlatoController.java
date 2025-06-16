package com.kaikan.controller;

import com.kaikan.generics.GenericController;
import com.kaikan.model.plato.Plato;
import com.kaikan.repository.PlatoRepository;
import com.kaikan.service.PlatoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/platos")
public class PlatoController extends GenericController<Plato, Long> {

    private final PlatoService platoService;
    private final PlatoRepository platoRepository;

    public PlatoController(PlatoService platoService, PlatoRepository platoRepository) {
        super(platoService);
        this.platoService = platoService;
        this.platoRepository = platoRepository;
    }

    @GetMapping("/{nombre}")
    public List<Plato> buscarPorCategoria(@PathVariable String nombre) {
        return platoRepository.buscarPlatoPorCategoria(nombre);
    }
}
