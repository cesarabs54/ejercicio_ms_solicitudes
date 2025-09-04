package co.com.bancolombia.model.gateway;

import co.com.bancolombia.model.Estado;
import java.util.UUID;
import reactor.core.publisher.Mono;

public interface EstadoRepository extends GenericGateway<Estado, UUID> {

    Mono<Estado> findByNombre(String nombre);

}
