package co.com.bancolombia.r2dbc.adapter;

import co.com.bancolombia.model.Estado;
import co.com.bancolombia.model.Solicitud;
import co.com.bancolombia.model.TipoPrestamo;
import co.com.bancolombia.model.gateway.EstadoRepository;
import co.com.bancolombia.model.gateway.SolicitudRepository;
import co.com.bancolombia.model.gateway.TipoPrestamoRepository;
import co.com.bancolombia.r2dbc.entity.SolicitudData;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import co.com.bancolombia.r2dbc.repository.SolicitudDataRepository;
import co.com.bancolombia.r2dbc.util.IdGeneratorUtil;
import java.util.UUID;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SolicitudDataRepositoryAdapter extends ReactiveAdapterOperations<
        Solicitud, SolicitudData, UUID, SolicitudDataRepository
        > implements SolicitudRepository {

    private final EstadoRepository estadoRepository;
    private final TipoPrestamoRepository tipoPrestamoRepository;


    protected SolicitudDataRepositoryAdapter(
            SolicitudDataRepository repository,
            ObjectMapper mapper, EstadoRepository estadoRepository,
            TipoPrestamoRepository tipoPrestamoRepository) {
        super(repository, mapper, d -> mapper.map(d, Solicitud.class));
        this.estadoRepository = estadoRepository;
        this.tipoPrestamoRepository = tipoPrestamoRepository;
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return super.repository.deleteById(id);
    }

    @Override
    public Mono<Solicitud> save(Solicitud entity) {
        if (entity.getIdSolicitud() != null) {
            var data = toData(entity);
            data.setIdEstado(entity.getEstado().getIdEstado());
            data.setIdTipoPrestamo(entity.getTipoPrestamo().getIdTipoPrestamo());
            return super.saveData(data).map(this::toEntity);
        }
        var generatedId = IdGeneratorUtil.generateDefaultUUID();
        entity.setIdSolicitud(generatedId);
        var data = toData(entity);
        data.setIdEstado(entity.getEstado().getIdEstado());
        data.setIdTipoPrestamo(entity.getTipoPrestamo().getIdTipoPrestamo());
        return super.saveData(data).map(this::toEntity);
    }

    @Override
    public Flux<Solicitud> findByIdEstado(UUID idEstado) {
        return super.repository.findByIdEstado(idEstado)
                .flatMap(data ->
                        // buscamos estado y tipoPrestamo en paralelo
                        Mono.zip(
                                        estadoRepository.findById(data.getIdEstado()),
                                        tipoPrestamoRepository.findById(data.getIdTipoPrestamo())
                                )
                                .map(tuple -> {
                                    var estado = mapper.map(tuple.getT1(), Estado.class);
                                    var tipoPrestamo = mapper.map(tuple.getT2(),
                                            TipoPrestamo.class);

                                    Solicitud solicitud = mapper.map(data, Solicitud.class);
                                    solicitud.setEstado(estado);
                                    solicitud.setTipoPrestamo(tipoPrestamo);

                                    return solicitud;
                                })
                );
    }

}
