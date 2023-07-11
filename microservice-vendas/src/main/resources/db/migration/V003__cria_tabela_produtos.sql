CREATE TABLE produtos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(255),
    titulo VARCHAR(255),
    valor DECIMAL(10, 2),
    status VARCHAR(50),
    usuario_id BIGINT,
    categoria_id BIGINT,

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);