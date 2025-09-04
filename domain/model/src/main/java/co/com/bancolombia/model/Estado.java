package co.com.bancolombia.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estado {

    private UUID idEstado;
    private String nombre;
    private String descripcion;
}
