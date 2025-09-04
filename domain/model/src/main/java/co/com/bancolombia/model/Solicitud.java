package co.com.bancolombia.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {

    private UUID idSolicitud;
    private BigDecimal monto;
    private Integer plazo;
    private String email;
    private String documentNumber;
    private Estado estado; // Relación con la tabla 'estados'
    private TipoPrestamo tipoPrestamo; // Relación con la tabla 'tipo_prestamo'
}
