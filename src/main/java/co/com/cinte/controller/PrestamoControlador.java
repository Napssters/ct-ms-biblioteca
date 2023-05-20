package co.com.cinte.controller;


import co.com.cinte.dto.DatosPrestamosDTO;
import co.com.cinte.dto.response.CrearPrestamoResponse;
import co.com.cinte.dto.response.PrestamoResponse;
import co.com.cinte.exceptions.BibliotecaException;
import co.com.cinte.service.PrestamoService;
import co.com.cinte.utils.ConstantesPrestamos;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {

    @Autowired
    private PrestamoService prestamoService;

    @ApiOperation(value = "Motodo para consultar prestamos", nickname = "consultar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = PrestamoResponse.class),
            @ApiResponse(code = 400, message = "400")
    })
    @GetMapping(value = "{idPrestamo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrestamoResponse> consultar (
            @PathVariable
            @NotNull()
            Integer idPrestamo) throws BibliotecaException {
            return new ResponseEntity<>(prestamoService.buscarPrestamo(idPrestamo), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Motodo para realizar un prestamo", nickname = "crear")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200", response = CrearPrestamoResponse.class),
            @ApiResponse(code = 400, message = ConstantesPrestamos.USUARIO_PRESTAMO)
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CrearPrestamoResponse> pertarLibro (
            @Valid
            @RequestBody
            DatosPrestamosDTO datosPrestamos
            ) throws BibliotecaException {
            return new ResponseEntity<>(prestamoService.crearPrestamo(datosPrestamos), HttpStatus.OK);
    }

}

