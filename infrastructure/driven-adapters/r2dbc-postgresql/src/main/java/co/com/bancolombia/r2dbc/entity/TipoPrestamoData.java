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
@Table("tipos_prestamos")
public class TipoPrestamoData implements Persistable<UUID> {

    private UUID idTipoPrestamo;
    private String nombre;
    private BigDecimal montoMinimo;
    private BigDecimal montoMaximo;
    private BigDecimal tasaInteres;
    private Boolean validacionAutomatica;

    @Transient
    @SuppressWarnings("java:S2065")
    private transient boolean isNew = true;

    @Override
    public UUID getId() {
        return idTipoPrestamo;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

}
