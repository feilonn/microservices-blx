CREATE TABLE
categorias
(
    ID BIGINT       NOT NULL AUTO_INCREMENT,
    TITULO          VARCHAR(255) NOT NULL,
    STATUS          VARCHAR(30) NOT NULL,
    DATA_CRIACAO    DATETIME NOT NULL,

    PRIMARY KEY (ID)
);