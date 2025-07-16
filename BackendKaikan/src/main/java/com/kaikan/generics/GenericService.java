package com.kaikan.generics;

import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Servicio genérico para operaciones CRUD con validaciones.
 *
 * @param <T>  Tipo de entidad que extiende BaseEntity.
 * @param <ID> Tipo del identificador (Long, UUID, etc.).
 */
public class GenericService<T extends BaseEntity, ID> {

    private final GenericRepository<T, ID> genericRepository;

    /**
     * Constructor con inyección del repositorio genérico.
     *
     * @param genericRepository repositorio inyectado.
     */
    public GenericService(GenericRepository<T, ID> genericRepository) {
        this.genericRepository = Objects.requireNonNull(genericRepository, "El repositorio no puede ser null");
    }

    /**
     * Lista todas las entidades.
     *
     * @return lista de entidades T.
     */
    public List<T> listar() {
        return genericRepository.findAll();
    }

    /**
     * Guarda una entidad.
     *
     * @param t entidad a guardar.
     * @throws IllegalArgumentException si la entidad es null.
     */
    public void guardar(T t) {
        Objects.requireNonNull(t, "La entidad a guardar no puede ser null");
        genericRepository.save(t);
    }

    /**
     * Elimina una entidad.
     *
     * @param t entidad a eliminar.
     * @throws IllegalArgumentException si la entidad es null o no tiene ID.
     */
    public void eliminar(T t) {
        Objects.requireNonNull(t, "La entidad a eliminar no puede ser null");
        if (t.getId() == null) {
            throw new IllegalArgumentException("La entidad a eliminar debe tener un ID");
        }
        genericRepository.delete(t);
    }

    /**
     * Actualiza una entidad existente.
     *
     * @param t entidad con datos actualizados.
     * @throws IllegalArgumentException si la entidad es null o no tiene ID.
     * @throws RuntimeException si no existe una entidad con el ID dado.
     */
    public void actualizar(T t) {
        Objects.requireNonNull(t, "La entidad a actualizar no puede ser null");
        if (t.getId() == null) {
            throw new IllegalArgumentException("La entidad a actualizar debe tener un ID");
        }

        Optional<T> buscar = genericRepository.findById((ID) t.getId());
        if (buscar.isPresent()) {
            genericRepository.save(t);
        } else {
            throw new RuntimeException("Entidad con ID " + t.getId() + " no encontrada para actualizar.");
        }
    }

    /**
     * Elimina una entidad por su ID.
     *
     * @param id identificador de la entidad a eliminar.
     * @throws IllegalArgumentException si el ID es null.
     */
    public void eliminarPorId(ID id) {
        Objects.requireNonNull(id, "El ID no puede ser null para eliminar");
        genericRepository.deleteById(id);
    }
}


