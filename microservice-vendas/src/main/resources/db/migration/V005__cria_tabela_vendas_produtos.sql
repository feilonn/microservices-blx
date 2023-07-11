CREATE TABLE vendas_produtos (
    venda_id BIGINT,
    produto_id BIGINT,
    PRIMARY KEY (venda_id, produto_id),
    FOREIGN KEY (venda_id) REFERENCES vendas(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);