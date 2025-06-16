package com.kaikan.controller;

import com.kaikan.generics.GenericController;
import com.kaikan.model.categoria.Categoria;
import com.kaikan.model.plato.Plato;
import com.kaikan.service.CategoriaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController extends GenericController <Categoria, Long> {
    private final CategoriaService categoriaService;
    public CategoriaController(CategoriaService categoriaService){
        super(categoriaService);
        this.categoriaService = categoriaService;
    }
}
