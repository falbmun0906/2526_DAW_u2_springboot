# Proyecto: Aplicación Spring Boot CRUD con Docker

## Objetivo
PRINCIPAL: Enseñar a los alumnos, dentro del contexto del módulo de "Despliegue de Aplicaciones Web", cómo desplegar una aplicación Spring Boot utilizando Docker.

SECUNDARIO: Crear una aplicación Spring Boot simple para probar el despliegue en Docker. La aplicación implementará un CRUD básico de usuarios con persistencia en archivo.

## Descripción del Proyecto

### Aplicación CRUD de Usuarios con Spring Boot 

El proyecto consistirá en desarrollar una aplicación web sencilla utilizando Spring Boot que permita realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre un recurso de "Usuario". La persistencia de los datos se realizará en un archivo JSON almacenado en el sistema de archivos del contenedor Docker.

Aunque el objetivo principal es la dockerización, la aplicación debe ser funcional y permitir a los usuarios interactuar con ella a través de una interfaz web básica. Por tanto, se tiene que proporcionar en el README.md del proyecto toda la documentación necesaria para que los alumnos puedan entender y seguir el desarrollo de la aplicación CRUD.

Esta documentación estará bajo el punto "## Desarrollo de la Aplicación CRUD" en el README.md del proyecto.

#### Backend
- **Framework**: Spring Boot
- **Funcionalidad**: CRUD completo de usuarios
- **Modelo de Datos**: Usuario con campos básicos (id, nombre, email, edad)
- **Persistencia**: Archivo JSON en el sistema de archivos (sin base de datos)
- **API REST**: Endpoints para crear, leer, actualizar y eliminar usuarios

#### Frontend/Presentación
- **Interfaz**: Páginas web simples con Thymeleaf o HTML estático
- **Funcionalidades**:
  - Formulario para crear usuario
  - Lista de usuarios existentes
  - Opciones para editar y eliminar usuarios
  - Diseño básico y funcional

### Dockerización
- **Dockerfile**: Para construir la imagen de la aplicación
- **Imagen**: Contener la aplicación Spring Boot empaquetada como WAR
- **Configuración**: Variables de entorno para configuración
- **Puerto**: Exponer puerto 8080
- **Volumen**: Configuración opcional para persistencia de datos
- **Comandos**: Instrucciones para construir y ejecutar la imagen Docker
- **Docker Compose**: Para orquestar el despliegue, aunque sea una aplicación sencilla. Dejando constancia de como se haría en caso de necesitar más servicios en el futuro, como por ejemplo una base de datos.
- Documentación detallada del proceso de dockerización: Generación del war, creación del Dockerfile, construcción de la imagen y ejecución del contenedor.
- Instrucciones para ejecutar la aplicación en un contenedor Docker
- Ejemplos de comandos Docker

La parte de dockerización es fundamental para el objetivo del proyecto, ya que se busca que los alumnos aprendan a desplegar aplicaciones web utilizando contenedores Docker. Por tanto, se debe prestar especial atención a la creación del Dockerfile y a las instrucciones para construir y ejecutar la imagen Docker. Se debe documentar todo el proceso de dockerización y despliegue en el README.md del proyecto. 

La documentación debe ser una guía paso a paso para que los alumnos puedan seguirla fácilmente y entender cada parte del proceso. Debe ser didáctica, clara y concisa, proporcionando ejemplos prácticos y explicaciones detalladas.

Esta documentación estará bajo el punto "## Documentación de Dockerización y Despliegue" en el README.md del proyecto.

## Estructura del Proyecto
Estructura actual del proyecto (usando Gradle):
```
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/springboot/
│   │   │       ├── Application.java
│   │   │       ├── ServletInitializer.java
│   │   │       ├── controller/
│   │   │       │   └── UserController.java (por implementar)
│   │   │       ├── model/
│   │   │       │   └── User.java (por implementar)
│   │   │       └── service/
│   │   │           └── UserService.java (por implementar)
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── Dockerfile (por crear)
├── docker-compose.yml (por crear)
├── .dockerignore (por crear)
├── AGENTS.md
├── HELP.md
└── README.md (por crear/actualizar)
```

## Requisitos Técnicos
- Java 17 o superior
- Gradle 8.x+ (wrapper incluido)
- Docker
- Spring Boot 3.5.6

## Endpoints API REST
- `GET /api/users` - Listar todos los usuarios
- `GET /api/users/{id}` - Obtener un usuario por ID
- `POST /api/users` - Crear un nuevo usuario
- `PUT /api/users/{id}` - Actualizar un usuario existente
- `DELETE /api/users/{id}` - Eliminar un usuario

## Endpoints Web
- `GET /` - Página principal con lista de usuarios
- `GET /users/new` - Formulario para crear usuario
- `GET /users/edit/{id}` - Formulario para editar usuario

## Pasos de Implementación
1. ✅ Crear estructura del proyecto Spring Boot con Gradle
2. ✅ Implementar modelo de datos (User)
3. ✅ Implementar servicio de persistencia en archivo JSON
4. ✅ Implementar controlador REST (API)
5. ✅ Implementar controlador Web
6. ✅ Añadir dependencia de Thymeleaf y crear vistas básicas
7. ✅ Probar la aplicación localmente
8. ✅ Crear Dockerfile
9. ✅ Crear docker-compose.yml
10. ✅ Crear .dockerignore
11. ✅ Construir imagen Docker
12. ✅ Probar el despliegue en contenedor Docker
13. ✅ Documentar todo el proceso en README.md

**Estado del Proyecto: COMPLETADO ✅**

## Comandos Gradle
```bash
# Compilar el proyecto
./gradlew build

# Generar el archivo WAR
./gradlew bootWar

# Ejecutar la aplicación localmente
./gradlew bootRun

# Limpiar el proyecto
./gradlew clean
```

## Comandos Docker
```bash
# Construir la imagen
docker build -t springboot-crud-app .

# Ejecutar el contenedor
docker run -p 8080:8080 springboot-crud-app

# Ejecutar con volumen persistente para datos
docker run -p 8080:8080 -v $(pwd)/data:/app/data springboot-crud-app

# Usar Docker Compose
docker-compose up -d

# Detener Docker Compose
docker-compose down
```

## Notas Adicionales
- La aplicación debe ser lo más simple posible
- Enfoque en funcionalidad básica y despliegue correcto
- Código limpio y bien estructurado
- Documentación clara en README.md
- El proyecto utiliza Gradle como herramienta de construcción
- El paquete base es `com.example.springboot` (no `com.example.crud`)
- Se empaquetará como WAR para despliegue en contenedor Tomcat
