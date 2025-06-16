package com.kaikan.service;

import com.kaikan.generics.GenericRepository;
import com.kaikan.generics.GenericService;
import com.kaikan.model.categoria.Categoria;
import com.kaikan.model.plato.Plato;
import com.kaikan.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService extends GenericService <Categoria, Long> {
    private final CategoriaRepository categoriaRepository;
    public CategoriaService(CategoriaRepository categoriaRepository){
        super(categoriaRepository);
        this.categoriaRepository = categoriaRepository;
    }
}
