# 🎮 Biblioteca Digital de Videojuegos

## 📖 Descripción del Proyecto
La Biblioteca Digital de Videojuegos es una plataforma centralizada (inspirada en sistemas como Steam) que permite a los usuarios gestionar, adquirir y reseñar videojuegos en un entorno digital[cite: 2]. El sistema está construido bajo una arquitectura distribuida de microservicios utilizando Spring Boot, garantizando escalabilidad, persistencia de datos independiente y separación funcional[cite: 2]. 

El desarrollo se enfoca en la estructuración de código bajo el patrón CSR (Controller-Service-Repository), la manipulación de bases de datos relacionales y la comunicación eficiente entre componentes independientes.

## 👥 Integrantes del Equipo
* **Mario Antonio Quintero** (SevenA7X)
* **[Nombre Real del Estudiante]** (DawbroDB) - *Actualizar con nombre completo*
* **[Nombre Real del Estudiante]** (HypershadiX) - *Actualizar con nombre completo*

## ⚙️ Arquitectura y Tecnologías
* **Framework:** Spring Boot (Java).
* **Patrón de Diseño:** CSR con separación estricta de responsabilidades por capas[cite: 3].
* **Persistencia:** JPA + Hibernate con bases de datos MySQL independientes para cada microservicio[cite: 2, 3].
* **Comunicación Interna:** OpenFeign para el consumo síncrono de endpoints entre microservicios[cite: 3].
* **Control de Versiones:** Git y GitHub[cite: 3].

## 🚀 Funcionalidades Implementadas
El ecosistema consta de 10 microservicios base (Catálogo, Compras, Estadísticas, Gestión de Usuarios, Historial, Licencias, Moderación, Notificaciones, Pagos y Reseñas). A la fecha, se destacan las siguientes implementaciones técnicas:

### 1. Microservicio de Catálogo (Puerto 8081)
* Operaciones CRUD completas para las entidades `Videojuego` y `Categoria`[cite: 3].
* Implementación del patrón DTO para evitar la exposición de entidades de base de datos en la capa REST[cite: 3].
* Búsquedas y filtros avanzados mediante parámetros REST (título, presupuesto, categoría).
* Emisión de respuestas coherentes utilizando `ResponseEntity` y códigos HTTP adecuados (ej. 404 Not Found para registros inexistentes)[cite: 3].

### 2. Microservicio de Reseñas
* Creación y almacenamiento de evaluaciones y puntuaciones por videojuego[cite: 2].
* Integración de comunicación remota: Utiliza **Feign Client** para verificar de manera síncrona la existencia de un videojuego en el Catálogo antes de persistir una nueva reseña, asegurando la integridad referencial distribuida[cite: 3].

## 🛠️ Pasos Detallados para Ejecutar la Aplicación

### Requisitos Previos
* Java 17 (o superior) instalado en el sistema.
* Maven para la gestión de dependencias.
* Motor de base de datos MySQL Server en ejecución.
* IDE (IntelliJ IDEA, VS Code o Eclipse)[cite: 3].

### Paso 1: Configuración de Base de Datos
Cada microservicio requiere su propia base de datos normalizada[cite: 2, 3]. Abra su cliente MySQL y ejecute los siguientes comandos para crear las bases de datos iniciales:
```sql
CREATE DATABASE catalogo_db;
CREATE DATABASE resena_db;
