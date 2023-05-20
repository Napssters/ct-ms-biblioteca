/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.cinte.service;

import co.com.cinte.dao.PrestamosDAO;
import co.com.cinte.dto.DatosPrestamosDTO;
import co.com.cinte.dto.response.CrearPrestamoResponse;
import co.com.cinte.dto.response.PrestamoResponse;
import co.com.cinte.exceptions.BibliotecaException;
import co.com.cinte.utils.ConstantesPrestamos;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nico
 */
@Service
public class PrestamoServiceImpl implements PrestamoService {
    
    @Autowired
    private PrestamosDAO prestamosDAO;

    /**
     * @param request
     * @return fecha y id de prestamo exitoso
     * @throws co.com.cinte.exceptions.BibliotecaException
     **/
    @Override
    public CrearPrestamoResponse crearPrestamo(DatosPrestamosDTO request) throws BibliotecaException {
        
        CrearPrestamoResponse response = null;
        try {
            String fecha = fechaDevolucion(request.getTipoUsuario());
            
            if (Boolean.TRUE.equals(consultarUsuario(request.getTipoUsuario()))) {
                if (request.getTipoUsuario() == 3){
                    if (consultarPrestamo(request.getIdentificacionUsuario()) != null) {
                        throw new BibliotecaException(ConstantesPrestamos.limitePrestamos(request.getIdentificacionUsuario()), 1);
                    } else {
                        guardarPrestamo(request, fecha);
                        response = crearResponse(fecha);
                    }
                    
                } else {
                    guardarPrestamo(request, fecha);
                    response = crearResponse(fecha);
                }
            } 
            return response;
        } catch(BibliotecaException e){
           throw new BibliotecaException(e.getMessage());
        }
    }

    /**
     * @param identificacionUsuario
     * @return existencia de un prestamo repetido
     *         en caso de que se cumpla la condicion
     *         de ser usuario invitado
     * @throws co.com.cinte.exceptions.BibliotecaException
     **/
    @Override
    public PrestamoResponse consultarPrestamo(String identificacionUsuario) throws BibliotecaException {
        
        PrestamoResponse response;
        
        response = prestamosDAO.findByIdentificacionUsuario(identificacionUsuario);
        return response;
    }
    
    /**
     * @param idPrestamo
     * @return datos del prestamo
     * @throws co.com.cinte.exceptions.BibliotecaException
     **/
    @Override
    public PrestamoResponse buscarPrestamo(Integer idPrestamo) throws BibliotecaException {
        PrestamoResponse response;
        try {
            response = prestamosDAO.findByIdPrestamo(idPrestamo);
            return response;
        } catch(Exception e) {
            throw new UnsupportedOperationException("metodo buscarPrestamo(): " + e);
        }
    }
    
    /**
     * @param request
     * @param fecha
     * @return void (Guardar en la bd la informacion enviada)
     * @throws java.lang.Exception
     **/
    private void guardarPrestamo(DatosPrestamosDTO request, String fecha){
        try {
            PrestamoResponse prestamoResponse = PrestamoResponse.builder()
                                                .isbn(request.getIsbn())
                                                .identificacionUsuario(request.getIdentificacionUsuario())
                                                .tipoUsuario(request.getTipoUsuario())
                                                .fechaMaximaDevolucion(fecha)
                                                .build();

            prestamosDAO.savePrestamo(prestamoResponse);
        } catch (Exception e) {
            throw new UnsupportedOperationException("metodo guardarPrestamo(): " + e);
        }
        
    }
    
    /**
     * @param request
     * @param fecha
     * @return response con fecha e id
     * @throws java.lang.Exception
     **/
    private CrearPrestamoResponse crearResponse(String fecha) {
        
        Integer id = prestamosDAO.asignarId();
        return CrearPrestamoResponse.builder()
                            .id(id)
                            .fechaMaximaDevolucion(fecha)
                            .build();
    }
    
    /**
     * @param tipoUsuario
     * @return Existencia del usuario
     * @throws java.lang.Exception
     **/
    private Boolean consultarUsuario(int tipoUsuario) throws BibliotecaException {
        return prestamosDAO.consultarTipoUsuario(tipoUsuario);
    }
	
    /** 
     * @param tipoUsuario
     * @return  fecha de devolucion del libro
     **/
    private String fechaDevolucion(int tipoUsuario){
        int dias = 0;
        if(tipoUsuario == 1){
            dias = 10;
        }
        if(tipoUsuario == 2){
            dias = 8;
        }
        if(tipoUsuario == 3){
            dias = 7;
        }
        LocalDate fechaPrestamo = LocalDate.now();
        fechaPrestamo = addDaysSkippingWeekends(fechaPrestamo, dias);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaPrestamo.format(formato);
    }
    
	/**
     * @param date
     * @param days
     * @return  dias habiles**/
    private LocalDate addDaysSkippingWeekends(LocalDate date, int days) {
        LocalDate result = date;
        int addedDays = 0;
        while (addedDays < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }
}
