package co.com.bancolombia.model.command;

public record SolicitudCommand(
        Double monto,
        Integer plazo,
        String email,
        String documentNumber,
        String tipoPrestamo) {

}
