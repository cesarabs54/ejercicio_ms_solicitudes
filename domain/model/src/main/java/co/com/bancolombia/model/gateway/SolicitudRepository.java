package co.com.bancolombia.model.gateway;

import co.com.bancolombia.model.Solicitud;
import java.util.UUID;
import reactor.core.publisher.Flux;

public interface SolicitudRepository extends GenericGateway<Solicitud, UUID> {

    Flux<Solicitud> findByIdEstado(UUID idEstado);

}
