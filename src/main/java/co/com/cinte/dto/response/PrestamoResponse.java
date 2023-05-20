/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.cinte.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Nico
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrestamoResponse {
    
    private Integer id;
    
    private String isbn;
    
    private String identificacionUsuario;
    
    private int tipoUsuario;
    
    private String fechaMaximaDevolucion;
    
}
