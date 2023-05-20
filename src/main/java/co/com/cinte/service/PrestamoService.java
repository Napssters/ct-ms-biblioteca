/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.cinte.service;

import co.com.cinte.dto.DatosPrestamosDTO;
import co.com.cinte.dto.response.CrearPrestamoResponse;
import co.com.cinte.dto.response.PrestamoResponse;
import co.com.cinte.exceptions.BibliotecaException;

/**
 *
 * @author Nico
 */
public interface PrestamoService {
    
    CrearPrestamoResponse crearPrestamo(DatosPrestamosDTO request) throws BibliotecaException;
    
    PrestamoResponse consultarPrestamo(String identificacionUsuario) throws BibliotecaException;
    
    PrestamoResponse buscarPrestamo (Integer idPrestamo) throws BibliotecaException;
    
}
