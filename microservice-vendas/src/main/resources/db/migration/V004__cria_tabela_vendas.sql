CREATE TABLE vendas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    data_venda DATETIME,
    usuario_id BIGINT,
    total_compra DECIMAL(10, 2),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);