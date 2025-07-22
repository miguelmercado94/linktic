
---

## 🏬 README — `inventario-service`

```markdown
# 📦 inventario-service

Servicio backend para la gestión de inventario, con integración a productos vía HTTP. Desarrollado con **Spring Boot WebFlux**, **R2DBC**, y documentación Swagger.

## 🚀 Tecnologías

- Java 17
- Spring Boot
- R2DBC con PostgreSQL
- Swagger UI (`springdoc-openapi`)
- Comunicación entre servicios vía HTTP (productos-service)
- Docker y Docker Compose

## 🔧 Variables de entorno

```env
INVENTARIO_DB_URL=r2dbc:postgresql://<host>:<puerto>/<database>
INVENTARIO_DB_USER=<usuario>
INVENTARIO_DB_PASS=<contraseña>

PRODUCTOS_SERVICE_URL=http://<host>:8080
