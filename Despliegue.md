## Práctica Docker / DockerHub

Esta práctica consiste en la creación y subida de imágenes a partir de un proyecto de Springboot dado, y a través del uso de GitHub Actions.

### Edición del código.

Se ha editado el fichero en ./src/main/resources/templates/index.html para añadir mi nombre y apellidos.

https://github.com/falbmun0906/2526_DAW_u2_springboot/blob/40a41d88d2ef36426c5800cf9a406b263d5c94a9/src/main/resources/templates/index.html#L12

### Automatización de la creación y subida de imágenes.

Para esta parte:

Se ha creado el fichero .yml en ./github/workflows/dockerCI.yml

https://github.com/falbmun0906/2526_DAW_u2_springboot/blob/0f896b8e36699855f9a64304b4804ad5bc296a83/.github/workflows/dockerCI.yml#L1-L30

En primer lugar, se define el trigger de la action, en este caso tanto 'on push' como 'on pull_request'. Con esto me aseguro que la imagen se actualice tanto al hacer push como pull request.

Después defino un flujo que se ejecutará sobre Ubuntu. Después preparo la configuración inicial de Docker con el action encontrada en la Marketplace de Github Actions 'docker/setup-buildx-action@v2'

Una vez hecho esto, configuro los secretos que usaré para loguearme. Para ello, me dirijo a la configuración del repositorio (Settings -> Secrets and variables -> Actions). Ahí defino variables para DOCKER_USERNAME y DOCKER_PASSWORD que uso en el 'job' de logeo a DockerHub.

Hecho esto, se hace:

```bash
      - name: Build and push images
        run: |
          docker compose build
          docker tag springboot-crud-app falbmun0906/springboot-crud-app:latest
          docker push falbmun0906/springboot-crud-app:latest
```

Lo que crea la imagen definida en el docker-compose.yml, le añade la etiqueta 'latest' y la 'pushea' a DockerHub.

**La imagen queda publicada en [este enlace](https://hub.docker.com/r/falbmun0906/springboot-crud-app)**

> Aunque dicha imagen, una vez levantada en local, no muestra nada. He revisado los logs y no he encontrado el motivo.
