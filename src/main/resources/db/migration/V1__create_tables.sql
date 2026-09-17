CREATE TABLE cliente
(
    cd_cliente BIGSERIAL PRIMARY KEY,
    nm_cliente VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE produto
(
    cd_produto BIGSERIAL PRIMARY KEY,
    nm_produto VARCHAR(150) NOT NULL,
    vl_preco NUMERIC(12, 2) NOT NULL CHECK (vl_preco >= 0)
);

CREATE TABLE venda
(
    cd_venda BIGSERIAL PRIMARY KEY,
    cd_cliente BIGINT NOT NULL REFERENCES cliente(cd_cliente),
    cd_produto BIGINT NOT NULL REFERENCES produto(cd_produto),
    qt_quantidade INTEGER NOT NULL CHECK (qt_quantidade > 0),
    vl_total NUMERIC(12, 2) NOT NULL CHECK (vl_total >= 0),
    dt_venda TIMESTAMP NOT NULL
);

CREATE INDEX idx_venda_cliente ON venda(cd_cliente);
CREATE INDEX idx_venda_produto ON venda(cd_produto);
CREATE INDEX idx_venda_data ON venda(dt_venda);
