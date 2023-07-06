CREATE TABLE venda (
    id bigint AUTO_INCREMENT,
    data_venda DATETIME,
    comprador_id bigint,
    total_compra DECIMAL(10, 2),
    FOREIGN KEY (comprador_id) REFERENCES usuario (id),

    primary key (id)
);