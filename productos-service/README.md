# 🛒 productos-service

Servicio backend para la gestión de productos, construido con **Spring Boot WebFlux**, **R2DBC**, y documentación automática vía **Swagger/OpenAPI**.

## 🚀 Tecnologías

- Java 17
- Spring Boot
- R2DBC con PostgreSQL
- Swagger UI (`springdoc-openapi`)
- Docker y Docker Compose

## 🔧 Variables de entorno

El servicio se conecta a PostgreSQL utilizando variables externas:

```env
PRODUCTOS_DB_URL=r2dbc:postgresql://<host>:<puerto>/<database>
PRODUCTOS_DB_USER=<usuario>
PRODUCTOS_DB_PASS=<contraseña>
