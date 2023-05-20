/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.cinte.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.com.cinte.dto.response.PrestamoResponse;
import co.com.cinte.exceptions.BibliotecaException;
import co.com.cinte.utils.ConstantesPrestamos;

/**
 *
 * @author Nico
 */
@Repository
public class PrestamosDAOImpl implements PrestamosDAO {
    
    @Autowired
    private JdbcTemplate template;

	@Override
    @SuppressWarnings("deprecation")
    public PrestamoResponse findByIdentificacionUsuario(String identificacionUsuario) {
        String sql = ConstantesPrestamos.CONSULTAR_PRESTAMO;
        try {
            return template.queryForObject(
                sql, 
                new Object[] {identificacionUsuario}, 
                new BeanPropertyRowMapper<>(PrestamoResponse.class)
            );
        } catch (EmptyResultDataAccessException e){
            return null;
        }
        
    }

    @Override
    public void savePrestamo(PrestamoResponse prestamoResponse) {
        String sql = ConstantesPrestamos.CREAR_PRESTAMO;
        Object data = new Object[] {
                prestamoResponse.getIsbn(), 
                prestamoResponse.getIdentificacionUsuario(),
                prestamoResponse.getTipoUsuario(),
                prestamoResponse.getFechaMaximaDevolucion()
            };
        template.update(
                sql,
                data
        );
    }

	@Override
	@SuppressWarnings("deprecation")
    public PrestamoResponse findByIdPrestamo(Integer idPrestamo) {
        String sql = ConstantesPrestamos.BUSCAR_PRESTAMO;
        return template.queryForObject(
            sql, 
            new Object[] {idPrestamo}, 
            new BeanPropertyRowMapper<>(PrestamoResponse.class)
        );
    }

    @Override
    public Integer asignarId() {
        String sql = ConstantesPrestamos.CONSULTAR_MAXID;
        return template.queryForObject(sql, Integer.class);
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public Boolean consultarTipoUsuario(int tipoUsuario) throws BibliotecaException {
        String sql = ConstantesPrestamos.BUSCAR_TIPO_USUARIO;
        try {
            return template.queryForObject(
                sql, 
                new Object[] {tipoUsuario}, 
                Boolean.class
            );
        } catch (EmptyResultDataAccessException e){
            throw new BibliotecaException(ConstantesPrestamos.USUARIO_PRESTAMO, 1);
        }
        
    }
}
