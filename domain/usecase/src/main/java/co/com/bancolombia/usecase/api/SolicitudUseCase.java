package co.com.bancolombia.usecase.api;

import co.com.bancolombia.model.Solicitud;
import co.com.bancolombia.model.gateway.SolicitudRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SolicitudUseCase implements GenericUseCase<Solicitud, UUID> {

    private final SolicitudRepository solicitudRepository;


    @Override
    public Mono<Void> deleteById(UUID id) {
        return solicitudRepository.deleteById(id);
    }

    @Override
    public Flux<Solicitud> getAll() {
        return solicitudRepository.findAll();
    }

    @Override
    public Mono<Solicitud> getById(UUID id) {
        return solicitudRepository.findById(id);
    }

    @Override
    public Mono<Solicitud> save(Solicitud entity) {
        return solicitudRepository.save(entity);
    }

    @Override
    public Mono<Solicitud> update(Solicitud entity) {
        return solicitudRepository.update(entity);
    }
}
