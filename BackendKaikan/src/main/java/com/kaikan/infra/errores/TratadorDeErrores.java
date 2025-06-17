package com.kaikan.infra.errores;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.sql.SQLException;


@RestControllerAdvice
public class TratadorDeErrores {
    //.PSQLException SqlExceptionHelper SqlExceptionHelper  IllegalArgumentException InvalidDataAccessApiUsageException
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores= e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();

        return ResponseEntity.badRequest().body(errores);
    }
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity tratarErrorNullPointer400(InvalidDataAccessApiUsageException e){
        var errores= e.getMessage();

        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity tratarErrorSqlException400(SQLException e){
        var errores= e.getMessage();

        return ResponseEntity.badRequest().body(errores);
    }



    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        };
    }








}
