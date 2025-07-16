package com.kaikan.controller;

import com.kaikan.generics.GenericController;
import com.kaikan.generics.GenericService;
import com.kaikan.model.categoria.Categoria;
import com.kaikan.model.plato.Plato;
import com.kaikan.repository.CategoriaRepository;
import com.kaikan.repository.PlatoRepository;
import com.kaikan.service.CategoriaService;
import com.kaikan.service.PlatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/platos")
public class PlatoController extends GenericController<Plato, Long> {

    private final PlatoService platoService;
    private final PlatoRepository platoRepository;
    private final CategoriaRepository categoriaRepository;

    public PlatoController(PlatoService platoService, PlatoRepository platoRepository,  CategoriaRepository categoriaRepository) {
        super(platoService);
        this.platoService = platoService;
        this.platoRepository = platoRepository;
        this.categoriaRepository = categoriaRepository;
    }


    /**
     * Busca platos por el nombre de su categoría.
     *
     * @param nombre Nombre de la categoría.
     * @return Lista de platos que pertenecen a la categoría especificada.
     */
    @GetMapping("/{nombre}")
    public List<Plato> buscarPorCategoria(@PathVariable String nombre) {
        // Retorna lista de platos filtrados por categoría
        return platoRepository.buscarPlatoPorCategoria(nombre);
    }

    /**
     * Crea un nuevo plato y lo guarda en la base de datos.
     *
     * @param plato Objeto Plato recibido en el cuerpo de la solicitud.
     * @return El plato guardado con su categoría asociada correctamente.
     * @throws ResponseStatusException si la categoría no existe.
     */
    @PostMapping
    public Plato crear(@RequestBody Plato plato) {
        // Buscar la categoría por ID, lanzar excepción si no existe
        Categoria categoria = categoriaRepository.findById(plato.getCategoria().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada"));

        // Asociar la categoría al plato
        plato.setCategoria(categoria);

        // Guardar el plato usando el servicio
        platoService.guardar(plato);

        // Retornar el plato guardado
        return plato;
    }

}
