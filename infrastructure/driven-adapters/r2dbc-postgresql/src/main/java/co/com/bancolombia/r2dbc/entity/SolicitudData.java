package co.com.bancolombia.r2dbc.entity;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("solicitudes")
public class SolicitudData implements Persistable<UUID> {

    private UUID idSolicitud;
    private BigDecimal monto;
    private Integer plazo;
    private String email;
    private UUID idEstado;
    private UUID idTipoPrestamo;

    @Transient
    @SuppressWarnings("java:S2065")
    private transient boolean isNew = true;

    @Override
    public UUID getId() {
        return idSolicitud;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

}
