package co.com.cinte;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolicitudPrestarLibroTest {

    private String isbn;
    
    private String identificacionUsuario;
    
    private int tipoUsuario;
}
