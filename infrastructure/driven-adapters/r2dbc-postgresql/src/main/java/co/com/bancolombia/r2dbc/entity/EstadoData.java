package co.com.bancolombia.r2dbc.entity;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("estados")
public class EstadoData implements Persistable<UUID> {
    @Id
    private UUID idEstado;
    private String nombre;
    private String descripcion;

    @Transient
    @SuppressWarnings("java:S2065")
    private transient boolean isNew = true;

    @Override
    public UUID getId() {
        return idEstado;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
