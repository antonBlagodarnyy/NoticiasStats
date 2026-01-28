# NoticiasStats
Estadístcas de noticieros españoles sin animo de lucro.

## Arquitectura

1. Base de datos.
2. Batch:
    - Scraper de las páginas de los principales noticieros españoles.
    - Cleaner para borrar las noticias de hace 7 días.
    - Batch Scheduler que lance los procesos 1 vez al día.
4. Api para transformar los datos en estadísticas.
5. Cliente público.

## Tecnologías

- Mysql.
- Springboot.
- Angular.

## Noticieros usados

- [RTVE](https://www.rtve.es/)
- [El Mundo](https://www.elmundo.es)
- [20 Minutos](https://www.20minutos.es/)
- [La Razón](https://www.larazon.es/)
- [El país](https://elpais.com/)
- [ABC](https://www.abc.es/)
- [El Diario](https://www.eldiario.es/)

## Estadísticas

- Número de noticias por día / semana / mes diferenciadas por noticiero.


## v1.1.0-next

### Añadidas estadísticas

- Países mas nombrados. 
- Clasificar todas las noticias por categoría.

### Mejoras de código

- Cambio List por Set para evitar filtro de noticias repetidas.
- Evita links con 404.

### Mejoras generales

- Limite de solicitudes en la api.

## v1.0.0-next

1. [ ] Codificación del cliente.
2. [ ] Codificación del scheduler.
3. [ ] Añadidos todos los noticieros.
4. [ ] Despliegue.

## v0.0.1-next

1. [x] Definición de los datos a recopilar.
2. [x] Definición de las estadísticas a servir.
3. [x] DDL de la bd.
4. [x] Definición del JPA del batch y de la api.
5. [x] Codificación del scraper.
6. [x] Codificación de la api.