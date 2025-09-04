package co.com.bancolombia.r2dbc.adapter;

import co.com.bancolombia.model.TipoPrestamo;
import co.com.bancolombia.model.gateway.TipoPrestamoRepository;
import co.com.bancolombia.r2dbc.entity.TipoPrestamoData;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import co.com.bancolombia.r2dbc.repository.TipoPrestamoDataRepository;
import java.util.UUID;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TipoPrestamoDataRepositoryAdapter extends ReactiveAdapterOperations<
        TipoPrestamo, TipoPrestamoData, UUID, TipoPrestamoDataRepository
        > implements TipoPrestamoRepository {

    protected TipoPrestamoDataRepositoryAdapter(TipoPrestamoDataRepository repository,
            ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, TipoPrestamo.class));
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return null;
    }

    @Override
    public Mono<TipoPrestamo> findByNombre(String nombre) {
        return super.repository.findByNombre(nombre).map(this::toEntity);
    }
}
