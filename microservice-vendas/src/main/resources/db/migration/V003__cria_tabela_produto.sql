CREATE TABLE produto (
    id BIGINT AUTO_INCREMENT,
    descricao VARCHAR(255),
    titulo VARCHAR(255),
    valor DECIMAL(10, 2),
    status VARCHAR(20),
    vendedor_id BIGINT,
    categoria_id BIGINT,

    PRIMARY KEY (id),

    FOREIGN KEY (vendedor_id) REFERENCES usuario(id),
    FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);