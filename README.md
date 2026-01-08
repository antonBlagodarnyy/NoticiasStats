# NoticiasStats
Estadístcas de noticieros españoles sin animo de lucro.

## Arquitectura

1. Base de datos.
2. Batch:
    - Scrapper de las páginas de los principales noticieros españoles.
    - Cleaner para borrar las noticias de hace 7 días.
    - Batch Scheduler que lance los procesos 1 vez al día.
4. Api para transformar los datos en estadísticas.
5. Cliente público.

## Tecnologías

- Mysql/Postgresql.
- Springboot.
- Angular.

## v0.0.1-next

1. [ ] Recopilación de páginas modelo.
2. [ ] Definición de los datos a recopilar.
3. [ ] Definición de las estadísticas a servir.
4. [ ] DML de la bd.
5. [ ] Definicion del JPA del batch y de la api.
6. [ ] Codificación del scrapper.
7. [ ] Codificación de la api.
8. [ ] Códificacion del cliente.
9. [ ] Despliegue.