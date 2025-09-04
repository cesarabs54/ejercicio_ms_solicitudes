package co.com.bancolombia.r2dbc.repository;

import co.com.bancolombia.r2dbc.entity.EstadoData;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface EstadoDataRepository extends R2dbcRepository<EstadoData, UUID> {

    Mono<EstadoData> findByNombre(String nombre);

}
