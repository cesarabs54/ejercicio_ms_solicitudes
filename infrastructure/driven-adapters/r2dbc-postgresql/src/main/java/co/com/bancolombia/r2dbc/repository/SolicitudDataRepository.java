package co.com.bancolombia.r2dbc.repository;

import co.com.bancolombia.r2dbc.entity.SolicitudData;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface SolicitudDataRepository extends R2dbcRepository<SolicitudData, UUID> {

}
