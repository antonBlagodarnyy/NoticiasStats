# NoticiasStats
Estadístcas de noticieros españoles sin animo de lucro.

## Arquitectura

1. Base de datos.
2. Batch:
    - Scraper de las páginas de los principales noticieros españoles.
    - Cleaner para borrar las noticias de hace 30 días.
    - Batch Scheduler que lance los procesos 1 vez al día.
3. Cron en linux para ejecutar el batch
4. Api para transformar los datos en estadísticas.
5. Cliente público.

## Tecnologías

- Mysql.
- Springboot.
- Angular.

## Noticieros usados

- [x] [RTVE](https://www.rtve.es/) 
- [x] [El Mundo](https://www.elmundo.es)
- [x] [20 Minutos](https://www.20minutos.es/)
- [x] [La Razón](https://www.larazon.es/)
-  ~~[ ] [El país](https://elpais.com/) ~~
- [x] [ABC](https://www.abc.es/)
- [x] [El Diario](https://www.eldiario.es/)

## Estadísticas

- Número de noticias por día / semana / mes diferenciadas por noticiero.
- Clasificar todas las noticias por categoría.

## v1.1.0-next

### Añadidas estadísticas

- Países mas nombrados.

### Mejoras de código

- Cambio List por Set para evitar filtro de noticias repetidas.
- Evita links con 404.
- Limite de solicitudes en la api.

## v1.0.0-next

1. [x] Codificación del cliente.
2. [x] Codificación del scheduler.
3. [x] Añadidos todos los noticieros.
4. [x] Despliegue.
5. [x] Añadido cron.

## v0.0.1-next

1. [x] Definición de los datos a recopilar.
2. [x] Definición de las estadísticas a servir.
3. [x] DDL de la bd.
4. [x] Definición del JPA del batch y de la api.
5. [x] Codificación del scraper.
6. [x] Codificación de la api.