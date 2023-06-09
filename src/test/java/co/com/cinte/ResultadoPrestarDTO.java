package co.com.cinte;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultadoPrestarDTO {
	
    private int id;
    
    private String fechaMaximaDevolucion;
    
}
