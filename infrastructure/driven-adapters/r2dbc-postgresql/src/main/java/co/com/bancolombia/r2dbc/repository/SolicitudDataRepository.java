package co.com.bancolombia.r2dbc.repository;

import co.com.bancolombia.r2dbc.entity.SolicitudData;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface SolicitudDataRepository extends R2dbcRepository<SolicitudData, UUID> {

    Flux<SolicitudData> findByIdEstado(UUID idEstado);

}
