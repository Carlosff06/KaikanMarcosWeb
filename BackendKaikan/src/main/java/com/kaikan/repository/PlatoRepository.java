package com.kaikan.repository;

import com.kaikan.generics.GenericRepository;
import com.kaikan.model.plato.Plato;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlatoRepository extends GenericRepository<Plato, Long> {
    @Query("SELECT p From Plato p WHERE p.categoria.nombre=:categoria")
    List<Plato> buscarPlatoPorCategoria(@Param("categoria") String categoria);
}
