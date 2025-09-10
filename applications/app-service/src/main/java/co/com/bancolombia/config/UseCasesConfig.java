package co.com.bancolombia.config;

import co.com.bancolombia.model.gateway.EstadoRepository;
import co.com.bancolombia.model.gateway.SolicitudRepository;
import co.com.bancolombia.model.gateway.TipoPrestamoRepository;
import co.com.bancolombia.usecase.ListarPrestamosUseCase;
import co.com.bancolombia.usecase.api.SolicitarPrestamoUseCase;
import co.com.bancolombia.usecase.api.SolicitudUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.bancolombia.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {

    @Bean
    public SolicitudUseCase solicitudUseCase(SolicitudRepository solicitudRepository) {
        return new SolicitudUseCase(solicitudRepository);
    }

    @Bean
    public SolicitarPrestamoUseCase solicitarPrestamoUseCase(
            EstadoRepository r1, SolicitudRepository r2, TipoPrestamoRepository r3) {
        return new SolicitarPrestamoUseCase(r1, r2, r3);
    }

    @Bean
    public ListarPrestamosUseCase listarPrestamosUseCase(SolicitudRepository solicitudRepository,
            EstadoRepository estadoRepository) {
        return new ListarPrestamosUseCase(solicitudRepository, estadoRepository);
    }
}
