package co.com.bancolombia.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SolicitudRequest(
        @NotNull
        Double monto,
        @NotNull
        Integer plazo,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String documentNumber,
        @NotBlank
        String tipoPrestamo
) {}
