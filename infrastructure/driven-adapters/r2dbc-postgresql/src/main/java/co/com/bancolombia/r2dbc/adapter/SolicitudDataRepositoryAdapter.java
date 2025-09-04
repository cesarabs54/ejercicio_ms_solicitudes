package co.com.bancolombia.r2dbc.adapter;

import co.com.bancolombia.model.Solicitud;
import co.com.bancolombia.model.gateway.SolicitudRepository;
import co.com.bancolombia.r2dbc.entity.SolicitudData;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import co.com.bancolombia.r2dbc.repository.SolicitudDataRepository;
import co.com.bancolombia.r2dbc.util.IdGeneratorUtil;
import java.util.UUID;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SolicitudDataRepositoryAdapter extends ReactiveAdapterOperations<
        Solicitud, SolicitudData, UUID, SolicitudDataRepository
        > implements SolicitudRepository {


    protected SolicitudDataRepositoryAdapter(
            SolicitudDataRepository repository,
            ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Solicitud.class));
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return super.repository.deleteById(id);
    }

    @Override
    public Mono<Solicitud> save(Solicitud entity) {
        if (entity.getIdSolicitud() != null) {
            return super.save(entity);
        }
        var generatedId = IdGeneratorUtil.generateDefaultUUID();
        entity.setIdSolicitud(generatedId);
        return super.save(entity);
    }
}
