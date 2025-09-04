package co.com.bancolombia.r2dbc.adapter;

import co.com.bancolombia.model.Estado;
import co.com.bancolombia.model.gateway.EstadoRepository;
import co.com.bancolombia.r2dbc.entity.EstadoData;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import co.com.bancolombia.r2dbc.repository.EstadoDataRepository;
import java.util.UUID;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class EstadoDataRepositoryAdapter extends ReactiveAdapterOperations<
        Estado, EstadoData, UUID, EstadoDataRepository
        > implements EstadoRepository {

    public EstadoDataRepositoryAdapter(EstadoDataRepository repository,
            ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Estado.class));
    }

    @Override
    public Mono<Estado> findByNombre(String nombre) {
        return repository.findByNombre(nombre)
                .map(this::toEntity);
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return Mono.empty();
    }
}
