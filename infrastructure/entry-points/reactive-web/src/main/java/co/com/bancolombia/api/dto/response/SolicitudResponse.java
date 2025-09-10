package co.com.bancolombia.api.dto.response;

import co.com.bancolombia.model.Solicitud;
import java.math.BigDecimal;
import java.util.UUID;

public record SolicitudResponse(
        UUID idSolicitud,
        BigDecimal monto,
        Integer plazo,
        String email,
        String documentNumber,
        String estado,
        String tipoPrestamo
) {

    public static SolicitudResponse fromModel(Solicitud solicitud) {
        return new SolicitudResponse(
                solicitud.getIdSolicitud(),
                solicitud.getMonto(),
                solicitud.getPlazo(),
                solicitud.getEmail(),
                solicitud.getDocumentNumber(),
                solicitud.getEstado() != null ? solicitud.getEstado().getNombre() : null,
                solicitud.getTipoPrestamo() != null ? solicitud.getTipoPrestamo().getNombre() : null
        );
    }
}
