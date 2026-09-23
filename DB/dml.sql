USE news_scraper_db;

DELETE FROM newspapers;
ALTER TABLE newspapers AUTO_INCREMENT = 1;

INSERT INTO newspapers (name)
VALUES 
  ('RTVE'),
  ('20Minutos'),
  ('ElMundo'),
  ('LaRazon'),
  ('ElPais'),
  ('ABC'),
  ('ElDiario');