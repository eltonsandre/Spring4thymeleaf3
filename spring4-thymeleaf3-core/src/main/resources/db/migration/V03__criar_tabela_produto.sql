CREATE TABLE PRODUTO (
	CODIGO BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
	COMISSAO NUMERIC(19,2) NOT NULL,
	DESCRICAO VARCHAR(50) NOT NULL,
	NOME VARCHAR(255) NOT NULL,
	QUANTIDADE_ESTOQUE INTEGER NOT NULL CHECK (QUANTIDADE_ESTOQUE<=9999),
	SKU VARCHAR(255) NOT NULL,
	VALOR NUMERIC(19,2) NOT NULL, PRIMARY KEY (CODIGO)
)


