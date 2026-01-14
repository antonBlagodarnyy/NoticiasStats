USE news_scraper_db;

DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS newspapers;

CREATE TABLE `newspapers`
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL UNIQUE,
    url VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE `articles`
(
    id      INT AUTO_INCREMENT NOT NULL,
    newspaper_id INT NOT NULL,
    headline VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL UNIQUE,
    published_at DATE NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_article_newspaper
        FOREIGN KEY  (newspaper_id)
            REFERENCES `newspapers` (id)
            ON DELETE CASCADE
);
