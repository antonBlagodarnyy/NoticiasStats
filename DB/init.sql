USE noticiasScrapper_db;

CREATE TABLE `periodico`
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(255) NOT NULL UNIQUE,
    link VARCHAR(255) NOT NULL
);

CREATE TABLE `articulo`
(
    id      INT          NOT NULL,
    titular VARCHAR(255) NOT NULL,
    link VARCHAR(255) NOT NULL,
    fecha DATE NOT NULL DEFAULT (CURRENT_DATE),
    PRIMARY KEY (id),
    CONSTRAINT fk_articulo_periodico
        FOREIGN KEY (id)
            REFERENCES `periodico` (id)
            ON DELETE CASCADE
);
