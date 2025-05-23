CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL -- Aqui deve ser armazenado um hash em produção
);

-- Tabela de categorias
CREATE TABLE categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
	tipo VARCHAR(10) CHECK (tipo IN ('entrada', 'saida')),
    usuario_id INT REFERENCES usuario(id) ON DELETE CASCADE
);


-- Tabela de contas
CREATE TABLE conta (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    saldo_inicial NUMERIC(10,2) NOT NULL,
    usuario_id INT REFERENCES usuario(id) ON DELETE CASCADE
);

ALTER TABLE conta RENAME COLUMN saldo_inicial TO saldo;

-- Tabela de transações
CREATE TABLE transacao (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(200),
    valor DECIMAL(10,2) NOT NULL,
    data DATE NOT NULL,
    tipo VARCHAR(10) CHECK (tipo IN ('entrada', 'saida')),
    conta_id INT NOT NULL REFERENCES conta(id),
    categoria_id INT NOT NULL REFERENCES categoria(id),
    usuario_id INT NOT NULL REFERENCES usuario(id)
);
