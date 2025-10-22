# 🚀 Guía de Inicio Rápido

## Opción 1: Ejecución Local (Sin Docker)

```bash
# 1. Compilar y ejecutar
./gradlew bootRun

# 2. Acceder a la aplicación
# Web: http://localhost:8080
# API: http://localhost:8080/api/users
```

## Opción 2: Docker (Recomendado)

### Usando Docker Compose (Más Fácil)

```bash
# 1. Construir y levantar la aplicación
docker-compose up -d

# 2. Ver logs
docker-compose logs -f

# 3. Acceder a la aplicación
# Web: http://localhost:8080
# API: http://localhost:8080/api/users

# 4. Detener
docker-compose down
```

### Usando Docker directamente

```bash
# 1. Construir la imagen
docker build -t springboot-crud-app .

# 2. Ejecutar el contenedor
docker run -d -p 8080:8080 -v $(pwd)/data:/app/data --name crud-app springboot-crud-app

# 3. Ver logs
docker logs -f crud-app

# 4. Detener y eliminar
docker rm -f crud-app
```

## Opción 3: Generar WAR para despliegue externo

```bash
# 1. Generar el archivo WAR
./gradlew bootWar

# 2. El WAR se encuentra en:
# build/libs/springboot-0.0.1-SNAPSHOT.war

# 3. Desplegar en Tomcat externo
# Copiar el WAR a la carpeta webapps de Tomcat
```

## Probar la API REST

```bash
# Listar usuarios
curl http://localhost:8080/api/users

# Crear usuario
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Juan Pérez","email":"juan@example.com","edad":30}'

# Obtener usuario por ID
curl http://localhost:8080/api/users/1

# Actualizar usuario
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Juan Actualizado","email":"juan.nuevo@example.com","edad":31}'

# Eliminar usuario
curl -X DELETE http://localhost:8080/api/users/1
```

## Solución de Problemas

### Puerto 8080 ocupado

```bash
# Opción 1: Cambiar puerto en docker-compose.yml
ports:
  - "8081:8080"  # Usar 8081 en el host

# Opción 2: Ver qué está usando el puerto
sudo lsof -i :8080
```

### Ver logs de errores

```bash
# Docker Compose
docker-compose logs

# Docker
docker logs crud-app

# Local
./gradlew bootRun
```

### Limpiar y reconstruir

```bash
# Limpiar build
./gradlew clean

# Reconstruir imagen Docker
docker-compose build --no-cache

# Limpiar imágenes Docker
docker system prune -a
```

## Estructura de Datos

Los usuarios se guardan en `data/users.json`:

```json
[
  {
    "id": 1,
    "nombre": "Juan Pérez",
    "email": "juan@example.com",
    "edad": 30
  }
]
```

## URLs Importantes

- **Aplicación Web**: http://localhost:8080
- **API REST - Listar usuarios**: http://localhost:8080/api/users
- **Formulario nuevo usuario**: http://localhost:8080/users/new

## Recursos Adicionales

Para más información detallada, consulta:
- **README.md**: Documentación completa de desarrollo y dockerización
- **AGENTS.md**: Especificaciones del proyecto y objetivos educativos
