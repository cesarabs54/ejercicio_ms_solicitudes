package co.com.bancolombia.r2dbc.repository;

import co.com.bancolombia.r2dbc.entity.TipoPrestamoData;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface TipoPrestamoDataRepository extends R2dbcRepository<TipoPrestamoData, UUID> {

    Mono<TipoPrestamoData> findByNombre(String nombre);

}
