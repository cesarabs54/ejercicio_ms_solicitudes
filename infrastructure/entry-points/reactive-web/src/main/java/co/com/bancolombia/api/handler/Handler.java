package co.com.bancolombia.api.handler;

import co.com.bancolombia.api.dto.request.SolicitudRequest;
import co.com.bancolombia.api.util.RequestValidator;
import co.com.bancolombia.model.command.SolicitudCommand;
import co.com.bancolombia.usecase.api.SolicitarPrestamoUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final SolicitarPrestamoUseCase solicitarPrestamoUseCase;
    private final RequestValidator requestValidator;
    private final ObjectMapper objectMapper;

    public Mono<ServerResponse> solicitarPrestamo(ServerRequest request) {

        return request.bodyToMono(SolicitudRequest.class)
                .flatMap(requestValidator::validate)
                .map(solicitudRequest -> objectMapper.convertValue(solicitudRequest,
                        SolicitudCommand.class))
                .flatMap(solicitarPrestamoUseCase::execute)
                .flatMap(solicitud -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(solicitud)
                );
    }
}
