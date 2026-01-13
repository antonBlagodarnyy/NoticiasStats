USE noticiasScrapper_db;

DELETE FROM periodico;
ALTER TABLE periodico AUTO_INCREMENT = 1;

INSERT INTO periodico (nombre, link)
VALUES ('RTVE', 'https://www.rtve.es/noticias/');


INSERT INTO periodico (nombre, link)
VALUES ('20Minutos', 'https://www.20minutos.es/');