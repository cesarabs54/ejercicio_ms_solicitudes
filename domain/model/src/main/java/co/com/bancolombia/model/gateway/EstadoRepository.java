package co.com.bancolombia.model.gateway;

import co.com.bancolombia.model.Estado;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EstadoRepository extends GenericGateway<Estado, UUID> {

    Mono<Estado> findByNombre(String nombre);
    Flux<Estado> findByNombreIn(Set<String> nombres);

}
