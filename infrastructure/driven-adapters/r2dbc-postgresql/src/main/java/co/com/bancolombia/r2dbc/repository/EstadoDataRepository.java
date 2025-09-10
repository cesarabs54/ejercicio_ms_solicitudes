package co.com.bancolombia.r2dbc.repository;

import co.com.bancolombia.r2dbc.entity.EstadoData;
import java.util.Collection;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EstadoDataRepository extends R2dbcRepository<EstadoData, UUID> {

    Mono<EstadoData> findByNombre(String nombre);

    Flux<EstadoData> findByNombreIn(Collection<String> nombres);

}
