/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.cinte.dao;

import co.com.cinte.dto.response.PrestamoResponse;
import co.com.cinte.exceptions.BibliotecaException;

/**
 *
 * @author Nico
 */
public interface PrestamosDAO {
    
    PrestamoResponse findByIdentificacionUsuario(String identificacionUsuario);
    
    void savePrestamo(PrestamoResponse prestamoResponse);
    
    PrestamoResponse findByIdPrestamo(Integer idPrestamo);

    Integer asignarId();
    
    Boolean consultarTipoUsuario(int tipoUsuario) throws BibliotecaException;
    
}
