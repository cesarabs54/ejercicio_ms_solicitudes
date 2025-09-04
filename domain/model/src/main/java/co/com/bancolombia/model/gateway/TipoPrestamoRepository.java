package co.com.bancolombia.model.gateway;

import co.com.bancolombia.model.TipoPrestamo;
import java.util.UUID;
import reactor.core.publisher.Mono;

public interface TipoPrestamoRepository extends GenericGateway<TipoPrestamo, UUID> {

    Mono<TipoPrestamo> findByNombre(String nombre);

}
