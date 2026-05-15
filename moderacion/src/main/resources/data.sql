INSERT INTO moderacion (contenido_id, tipo_contenido, resultado, observaciones, fecha_revision) 
VALUES (1, 'RESENA', 'APROBADO', 'Contenido adecuado y respetuoso.', NOW());

INSERT INTO moderacion (contenido_id, tipo_contenido, resultado, observaciones, fecha_revision) 
VALUES (2, 'RESENA', 'RECHAZADO', 'Contiene lenguaje inapropiado.', NOW());

INSERT INTO moderacion (contenido_id, tipo_contenido, resultado, observaciones, fecha_revision) 
VALUES (3, 'RESENA', 'PENDIENTE', 'Requiere revisión manual por ambigüedad.', NOW());