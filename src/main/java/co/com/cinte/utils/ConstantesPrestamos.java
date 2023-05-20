/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.cinte.utils;

import lombok.Data;

/**
 *
 * @author Nico
 */
@Data
public class ConstantesPrestamos {
    
    public static final String USUARIO_PRESTAMO = "Tipo de usuario no permitido en la biblioteca";
    
    /******************
     * Consultas Sql
     ******************/
     public static String BUSCAR_TIPO_USUARIO = "select (tipoUsuario) from usuarios where tipoUsuario=?";
    
    public static final String CONSULTAR_PRESTAMO = "select * from prestamo where identificacionUsuario=?";
    
    public static final String CONSULTAR_MAXID = " select max(id) from prestamo;";
            
    public static final String BUSCAR_PRESTAMO = "select * from prestamo where id=?";
    
    public static final String CREAR_PRESTAMO = "insert into prestamo("
                                                    + "isbn, "
                                                    + "identificacionUsuario, "
                                                    + "tipoUsuario, "
                                                    + "fechaMaximaDevolucion) "
                                                + "values(?,?,?,?)";
    
    public static String LIMITE_PRESTAMOS(String identificacion){
        return "El usuario con identificación "
                + identificacion
                + " ya tiene un libro prestado por lo cual "
                + "no se le puede realizar otro préstamo";
    }
    
}
