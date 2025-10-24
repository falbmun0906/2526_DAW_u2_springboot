# Etapa 1: Construcción de la aplicación
FROM gradle:8.5-jdk17 AS builder

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración de Gradle y código fuente
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
COPY src ./src

# Dar permisos de ejecución a gradlew y construir la aplicación
RUN chmod +x gradlew && ./gradlew bootWar --no-daemon

# Etapa 2: Imagen final con Tomcat
FROM tomcat:10.1-jdk17

# Información del mantenedor
LABEL maintainer="DAW Course"
LABEL description="Aplicación CRUD Spring Boot con Docker"
LABEL version="1.0"

# Variables de entorno
ENV APP_DATA_DIR=/app/data
ENV APP_DATA_FILE=/app/data/users.json

# Crear directorio para datos
RUN mkdir -p ${APP_DATA_DIR}

# Eliminar aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar el WAR generado desde la etapa de construcción
COPY --from=builder /app/build/libs/*.war /usr/local/tomcat/webapps/ROOT.war

# Exponer el puerto 8080
EXPOSE 8080

# Crear un volumen para persistencia de datos
VOLUME ["/app/data"]

# Comando para iniciar Tomcat
CMD ["catalina.sh", "run"]
