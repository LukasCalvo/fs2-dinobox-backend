# Dinobox backend

Spring Boot + MySQL + JPA.

## Base de datos

En MySQL:

```sql
CREATE DATABASE dinobox_db;
CREATE USER 'dinobox_user'@'localhost' IDENTIFIED BY 'dinobox_pass';
GRANT ALL PRIVILEGES ON dinobox_db.* TO 'dinobox_user'@'localhost';
FLUSH PRIVILEGES;
```

La conexion esta en `src/main/resources/application.properties`. La tabla se crea sola al arrancar.

## Como correrlo

Hace falta Java 21.

```bash
./mvnw spring-boot:run
```

Queda en http://localhost:8080

## API

Swagger: http://localhost:8080/swagger-ui.html

- POST /api/v1/encomiendas
- GET /api/v1/encomiendas
- GET /api/v1/encomiendas/{codigo}
- PUT /api/v1/encomiendas/{id}
- DELETE /api/v1/encomiendas/{id}

Estados: EN_RECEPCION, EN_DESPACHO, EN_TRANSITO, ENTREGADO.

Al crear, si no mandas estado, queda EN_RECEPCION. La respuesta trae codigoSeguimiento.
