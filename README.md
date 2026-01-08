# NoticiasStats
Estadístcas de noticieros españoles sin animo de lucro.

## Arquitectura

1. Base de datos.
2. Batches:
    - Scrapper de las páginas de los principales noticieros españoles.
    - Cleaner para borrar las noticias de hace 7 días.
3. Batch Scheduler que lance los batches 1 vez al día.
4. Api para transformar los datos en estadísticas.
5. Cliente público.

## Tecnologías

- Mysql/Postgresql.
- Springboot.
- Angular.