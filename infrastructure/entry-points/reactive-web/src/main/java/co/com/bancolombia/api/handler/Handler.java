package co.com.bancolombia.api.handler;

import co.com.bancolombia.api.dto.request.SolicitudRequest;
import co.com.bancolombia.api.dto.response.PageResponse;
import co.com.bancolombia.api.dto.response.SolicitudResponse;
import co.com.bancolombia.api.util.RequestValidator;
import co.com.bancolombia.model.command.SolicitudCommand;
import co.com.bancolombia.usecase.ListarPrestamosUseCase;
import co.com.bancolombia.usecase.api.SolicitarPrestamoUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final SolicitarPrestamoUseCase solicitarPrestamoUseCase;
    private final ListarPrestamosUseCase listarPrestamosUseCase;
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

    public Mono<ServerResponse> listarPrestamos(ServerRequest request) {
        int page = request.queryParam("page").map(Integer::parseInt).orElse(0);
        int size = request.queryParam("size").map(Integer::parseInt).orElse(10);

        Mono<Long> total = listarPrestamosUseCase.count();
        Flux<SolicitudResponse> data = listarPrestamosUseCase.execute(page, size)
                .map(SolicitudResponse::fromModel);

        return Mono.zip(total, data.collectList())
                .flatMap(tuple -> {
                    long totalElements = tuple.getT1();
                    var solicitudes = tuple.getT2();

                    PageResponse<SolicitudResponse> response = new PageResponse<>(
                            solicitudes,
                            page,
                            size,
                            totalElements,
                            (int) Math.ceil((double) totalElements / size)
                    );

                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(response);
                });
    }


}
