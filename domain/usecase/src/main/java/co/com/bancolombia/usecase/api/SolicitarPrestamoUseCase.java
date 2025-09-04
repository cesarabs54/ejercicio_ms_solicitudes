package co.com.bancolombia.usecase.api;

import co.com.bancolombia.model.Solicitud;
import co.com.bancolombia.model.command.SolicitudCommand;
import co.com.bancolombia.model.gateway.EstadoRepository;
import co.com.bancolombia.model.gateway.SolicitudRepository;
import co.com.bancolombia.model.gateway.TipoPrestamoRepository;
import java.math.BigDecimal;
import reactor.core.publisher.Mono;

public class SolicitarPrestamoUseCase {

    private final EstadoRepository estadoRepository;
    private final SolicitudRepository solicitudRepository;
    private final TipoPrestamoRepository tipoPrestamoRepository;

    public SolicitarPrestamoUseCase(EstadoRepository estadoRepository,
            SolicitudRepository solicitudRepository,
            TipoPrestamoRepository tipoPrestamoRepository) {
        this.estadoRepository = estadoRepository;
        this.solicitudRepository = solicitudRepository;
        this.tipoPrestamoRepository = tipoPrestamoRepository;
    }

    public Mono<Solicitud> execute(SolicitudCommand solicitud) {
        // Busca el estado "Pendiente de revisión" de forma reactiva
        var estadoMono = estadoRepository.findByNombre("Pendiente de revisión");

        // Busca el tipo de préstamo de forma reactiva
        var tipoPrestamoMono = tipoPrestamoRepository.findByNombre(solicitud.tipoPrestamo())
                .switchIfEmpty(Mono.error(new IllegalArgumentException(
                        "Tipo de préstamo no válido: " + solicitud.tipoPrestamo())));

        // Combina los resultados de ambos Mono
        return estadoMono.zipWith(tipoPrestamoMono, (estado, tipoPrestamo) -> new Solicitud(
                null, // Generar un nuevo ID para la solicitud
                BigDecimal.valueOf(solicitud.monto()),
                solicitud.plazo(),
                solicitud.email(),
                solicitud.documentNumber(),
                estado,
                tipoPrestamo
        )).flatMap(solicitudRepository::save); // Guarda la solicitud y devuelve el Mono resultante
    }

}
