# 🎮 Biblioteca Digital de Videojuegos

## 📖 Descripción del Proyecto
La Biblioteca Digital de Videojuegos es una plataforma centralizada (inspirada en sistemas como Steam) que permite a los usuarios gestionar, adquirir y reseñar videojuegos en un entorno digital. El sistema está construido bajo una arquitectura distribuida de microservicios utilizando Spring Boot, garantizando escalabilidad, persistencia de datos independiente y separación funcional.

El desarrollo se enfoca en la estructuración de código bajo el patrón CSR (Controller-Service-Repository), la manipulación de bases de datos relacionales y la comunicación eficiente entre componentes independientes.

## 👥 Integrantes del Equipo
* **MARIO ANTONIO QUINTERO ALMENDRAS** (SevenA7X)
* MATIAS ALFONSO PENA SANCHEZ (DawbroDB) 
* **JAVIER IGNACIO ASTUDILLO BRITO** (HypershadiX) 

## ⚙️ Arquitectura y Tecnologías
* **Framework:** Spring Boot (Java).
* **Patrón de Diseño:** CSR con separación estricta de responsabilidades por capas.
* **Persistencia:** JPA + Hibernate con bases de datos MySQL independientes para cada microservicio.
* **Comunicación Interna:** OpenFeign para el consumo síncrono de endpoints entre microservicios.
* **Control de Versiones:** Git y GitHub.

## 🚀 Funcionalidades Implementadas
El ecosistema consta de 10 microservicios base (Catálogo, Compras, Estadísticas, Gestión de Usuarios, Historial, Licencias, Moderación, Notificaciones, Pagos y Reseñas). A la fecha, se destacan las siguientes implementaciones técnicas:

### 1. Microservicio de Catálogo (Puerto 8081)
* Operaciones CRUD completas para las entidades `Videojuego` y `Categoria`.
* Implementación del patrón DTO para evitar la exposición de entidades de base de datos en la capa REST.
* Búsquedas y filtros avanzados mediante parámetros REST (título, presupuesto, categoría).
* Emisión de respuestas coherentes utilizando `ResponseEntity` y códigos HTTP adecuados (ej. 404 Not Found para registros inexistentes).

### 2. Microservicio de Reseñas
* Creación y almacenamiento de evaluaciones y puntuaciones por videojuego.
* Integración de comunicación remota: Utiliza **Feign Client** para verificar de manera síncrona la existencia de un videojuego en el Catálogo antes de persistir una nueva reseña, asegurando la integridad referencial distribuida.

## 🛠️ Pasos Detallados para Ejecutar la Aplicación

### Requisitos Previos
* Java 17 (o superior) instalado en el sistema.
* Maven para la gestión de dependencias.
* Motor de base de datos MySQL Server en ejecución.
* IDE (IntelliJ IDEA, VS Code o Eclipse).

### Paso 1: Configuración de Base de Datos
Cada microservicio requiere su propia base de datos normalizada. Abra su cliente MySQL y ejecute los siguientes comandos para crear las bases de datos iniciales:
```sql
CREATE DATABASE catalogo_db;
CREATE DATABASE resena_db;
