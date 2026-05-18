# 🎮 Biblioteca Digital de Videojuegos

> Plataforma centralizada de distribución digital para la gestión, adquisición y evaluación de videojuegos. Construida bajo una arquitectura de microservicios con Spring Boot, prioriza la escalabilidad, la separación funcional y la resiliencia técnica.

---

## 👥 Equipo y Responsabilidades

| Desarrollador | Alias | Microservicios a Cargo |
| :--- | :--- | :--- |
| **Mario Quintero** | `SevenA7X` | Catálogo, Reseñas, Moderación |
| **Matias Pena** | `DawbroDB` | Pagos, Compras, Historial, Licencias |
| **Javier Astudillo** | `HypershadiX` | Usuarios, Notificaciones, Estadísticas |

---

## ⚙️ Arquitectura y Tecnologías

El proyecto implementa el patrón **CSR (Controller-Service-Repository)**, garantizando una estricta separación de responsabilidades lógicas y de datos.

| Componente | Tecnología Implementada |
| :--- | :--- |
| **Framework Core** | Spring Boot (Java) |
| **Persistencia** | JPA + Hibernate |
| **Bases de Datos** | MySQL (Instancias independientes por microservicio) |
| **Comunicación Remota** | Spring Cloud OpenFeign (Síncrona) |
| **Control de Versiones** | Git y GitHub |

### 📦 Dependencias y Versiones (`pom.xml`)

* **`spring-boot-starter-parent` (3.3.5):** Administrador global de versiones.
* **`java` (Versión 21):** Entorno de ejecución.
* **`springdoc-openapi-starter-webmvc-ui` (2.5.0):** Interfaz gráfica de Swagger.
* **`spring-boot-starter-data-jpa`:** Mapeo objeto-relacional (ORM).
* **`mysql-connector-j`:** Driver oficial de conectividad.
* **`spring-boot-starter-validation`:** Implementación de validaciones JSR-380.
* **`lombok`:** Generación automática de código *boilerplate*.

### 🛡️ Resiliencia y Manejo Centralizado de Errores

* **Circuit Breaker:** Implementación de **Resilience4j** con clases *Fallback* en clientes Feign para garantizar la alta disponibilidad y una degradación controlada ante fallos de red.
* **Manejo Centralizado:** Uso de `@ControllerAdvice` para capturar excepciones de forma global, estandarizando las respuestas HTTP (ej. `400 Bad Request`, `404 Not Found`).
* **Trazabilidad:** Integración de **SLF4J** para el registro estructurado de eventos (`INFO`, `ERROR`, `WARN`) en la capa de servicio.

---

## 🚀 Ecosistema de Microservicios

| Puerto | Microservicio | Descripción Detallada |
| :---: | :--- | :--- |
| `8081` | **catalogo** | Gestión centralizada de videojuegos y categorías. Operaciones CRUD completas, implementación del patrón DTO, búsquedas/filtros avanzados mediante parámetros REST y manejo de respuestas HTTP coherentes. |
| `8082` | **resena** | Plataforma de evaluación comunitaria. Creación y almacenamiento de comentarios y puntuaciones. Utiliza Feign Client para verificar de manera síncrona la existencia de un videojuego en el Catálogo antes de persistir, asegurando la integridad referencial externa. |
| `8083` | **moderacion** | Control de calidad y normativas. Permite la revisión del contenido generado por los usuarios (como reseñas inapropiadas), aprobando, rechazando o modificando el estado de dichas publicaciones. |
| `8084` | **Pagos** | Transacciones financieras. Valida y procesa los cobros de las órdenes generadas, confirmando el éxito o fracaso de la operación económica. |
| `8085` | **Compras** | Procesamiento de intenciones de adquisición. Administra los carritos de compra y consolida las órdenes antes de derivarlas al servicio de pagos. |
| `8086` | **HistorialCompras** | Registro inmutable de actividad. Mantiene la trazabilidad de las transacciones pasadas y la biblioteca consolidada de los videojuegos adquiridos por cada usuario. |
| `8087` | **LicenciasYDescargas** | Control de acceso y DRM (Digital Rights Management). Valida la propiedad de los juegos comprados y gestiona los permisos para la descarga del contenido. |
| `8088` | **gestion-usuarios** | Administración de identidad. Controla el registro de cuentas, perfiles, credenciales de acceso y asignación de roles dentro del sistema. |
| `8089` | **Notificaciones** | Sistema de alertas y comunicación. Encargado de despachar mensajes transaccionales (ej. confirmaciones de compra, alertas de moderación) a los usuarios correspondientes. |
| `8090` | **Estadisticas-Uso** | Recopilación y análisis de métricas. Genera reportes sobre ventas, popularidad de videojuegos, calificaciones promedio y actividad general de la plataforma. |

---

## 🛠️ Despliegue y Ejecución

### Requisitos Previos
* Java 21 instalado en el entorno.
* Apache Maven (Gestión de dependencias).
* Motor MySQL Server en ejecución (Puerto 3306).
* IDE compatible (IntelliJ IDEA, VS Code, Eclipse).

### Paso 1: Configuración de Bases de Datos
Ejecute el siguiente *script* en su cliente MySQL para crear los esquemas independientes:

```sql
CREATE DATABASE catalogo_db;
CREATE DATABASE resena_db;
CREATE DATABASE gestion_usuarios_db;
CREATE DATABASE estadisticas_uso_db;
CREATE DATABASE notificaciones_db;
CREATE DATABASE resenas_db;
CREATE DATABASE moderacion_db;
CREATE DATABASE db_pagos;
CREATE DATABASE db_compras;
CREATE DATABASE db_historialcompras;
CREATE DATABASE db_licencias;
CREATE DATABASE gestion_usuarios_db;
CREATE DATABASE notificaciones_db;
CREATE DATABASE estadisticas_uso_db;
```

### Paso 2: Ejecución de los Microservicios
1. Clone el repositorio en su máquina local.
2. Inicie cada aplicación mediante su clase principal anotada con `@SpringBootApplication`. 
   * **Nota importante:** Se recomienda iniciar primero el microservicio de **Catálogo**, ya que los demás componentes realizan validaciones de integridad contra él.

### Paso 3: Documentación y Pruebas (Swagger UI)
Acceda a la documentación interactiva de cada servicio mediante las siguientes URLs:

* **Catálogo:** `http://localhost:8081/swagger-ui/index.html`
* **Reseñas:** `http://localhost:8082/swagger-ui/index.html`
* **Moderación:** `http://localhost:8083/swagger-ui/index.html`
* **Pagos:** `http://localhost:8084/swagger-ui/index.html`
* **Compras:** `http://localhost:8085/swagger-ui/index.html`
* **Historial:** `http://localhost:8086/swagger-ui/index.html`
* **Licencias:** `http://localhost:8087/swagger-ui/index.html`
* **Usuarios:** `http://localhost:8088/swagger-ui/index.html`
* **Notificaciones:** `http://localhost:8089/swagger-ui/index.html`
* **Estadísticas:** `http://localhost:8090/swagger-ui/index.html`

