package co.com.bancolombia.usecase;

import co.com.bancolombia.model.Estado;
import co.com.bancolombia.model.Solicitud;
import co.com.bancolombia.model.gateway.EstadoRepository;
import co.com.bancolombia.model.gateway.SolicitudRepository;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ListarPrestamosUseCase {

    private final SolicitudRepository solicitudRepository;
    private final EstadoRepository estadoRepository;

    /**
     * Lista solicitudes filtradas por los estados que requieren revisión. Aplica paginación en
     * memoria.
     */
    public Flux<Solicitud> execute(int page, int size) {
        var estadosRevisables = Set.of("Pendiente de revisión", "Rechazadas", "Revisión manual");

        // Buscar los estados por nombre
        Flux<UUID> estadosIds = estadoRepository.findByNombreIn(estadosRevisables)
                .map(Estado::getIdEstado);

        // Por cada estadoId, buscar solicitudes y combinarlas
        Flux<Solicitud> solicitudes = estadosIds
                .flatMap(solicitudRepository::findByIdEstado);

        // Paginación: saltar y limitar
        return solicitudes.skip((long) page * size).take(size);
    }

    /**
     * Total de registros (para armar respuesta de paginación).
     */
    public Mono<Long> count() {
        var estadosRevisables = Set.of("Pendiente de revisión", "Rechazadas", "Revisión manual");
        return estadoRepository.findByNombreIn(estadosRevisables)
                .flatMap(estado -> solicitudRepository.findByIdEstado(estado.getIdEstado()))
                .count();
    }
}
