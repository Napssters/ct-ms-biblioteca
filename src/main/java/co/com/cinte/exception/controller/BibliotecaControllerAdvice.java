/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.cinte.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.com.cinte.dto.response.CrearPrestamoResponse;
import co.com.cinte.exceptions.BibliotecaException;

/**
 *
 * @author Nico
 */
@RestControllerAdvice
public class BibliotecaControllerAdvice {
    
    @ExceptionHandler(BibliotecaException.class)
    public ResponseEntity<Object> processBibliotecaException(BibliotecaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CrearPrestamoResponse.builder()
                                    .mensaje(ex.getMessage())
                                    .build()
                );
    }
}
