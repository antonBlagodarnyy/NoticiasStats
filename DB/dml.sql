USE news_scraper_db;

DELETE FROM newspapers;
ALTER TABLE newspapers AUTO_INCREMENT = 1;

INSERT INTO newspapers (name, url)
VALUES 
  ('RTVE', 'https://www.rtve.es/noticias/'),
  ('20Minutos', 'https://www.20minutos.es/');