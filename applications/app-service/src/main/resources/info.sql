CREATE TABLE estados
(
    id_estado   UUID PRIMARY KEY,
    nombre      VARCHAR(255) NOT NULL,
    descripcion TEXT
);

CREATE TABLE tipo_prestamo
(
    id_tipo_prestamo      UUID PRIMARY KEY,
    nombre                VARCHAR(255)   NOT NULL,
    monto_minimo          NUMERIC(10, 2) NOT NULL,
    monto_maximo          NUMERIC(10, 2) NOT NULL,
    tasa_interes          NUMERIC(5, 2)  NOT NULL,
    validacion_automatica BOOLEAN
);

CREATE TABLE solicitud
(
    id_solicitud     UUID PRIMARY KEY,
    monto            NUMERIC(10, 2) NOT NULL,
    plazo            INTEGER        NOT NULL,
    email            VARCHAR(255)   NOT NULL,
    document_number  VARCHAR(255)   NOT NULL,
    id_estado        UUID           NOT NULL,
    id_tipo_prestamo UUID           NOT NULL,
    FOREIGN KEY (id_estado) REFERENCES estados (id_estado),
    FOREIGN KEY (id_tipo_prestamo) REFERENCES tipo_prestamo (id_tipo_prestamo)
);

INSERT INTO estados (id_estado, nombre, descripcion)
VALUES ('9baecc64-cf93-4969-8ef8-c231f86053c5', 'Pendiente de revisión',
        'La solicitud ha sido enviada y está a la espera de ser revisada.'),
       ('8451cbde-bfbd-4f65-9cc4-523cbb1613ad', 'Aprobado',
        'La solicitud ha sido revisada y aceptada.'),
       ('c08ed6bb-cd0f-4287-8dc9-b4c05383cdbd', 'Rechazado',
        'La solicitud no cumple con los requisitos y ha sido denegada.'),
       ('0ffb3511-0dd0-4777-924a-c02d825a3ae4', 'Aprobado con condiciones',
        'La solicitud ha sido aceptada con condiciones adicionales.'),
       ('8fa5f48c-7152-42cb-82d0-18d575a2f215', 'Cancelado por el solicitante',
        'La solicitud fue cancelada por el usuario antes de su aprobación.'),
       ('d2f7d90a-6f24-4a63-8b94-1a8d9db3a6f5', 'Revisión manual',
        'La solicitud requiere revisión manual por parte de un asesor.');


INSERT INTO tipo_prestamos (id_tipo_prestamo, nombre, monto_minimo, monto_maximo, tasa_interes,
                            validacion_automatica)
VALUES ('9ef1b0d0-b42b-41f6-9c68-5f94d87db5fd', 'PERSONAL', 1000.00, 50000.00, 12.50, TRUE),
       ('cfc3f6fe-d6a3-4c8b-92cf-95397ae02f11', 'HIPOTECARIO', 50000.00, 500000.00, 8.75, FALSE),
       ('9f6dbef9-487d-456b-b0ce-1b597f9267d3', 'AUTOMOTRIZ', 5000.00, 150000.00, 10.00, TRUE);