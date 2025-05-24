CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE contas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    saldo DECIMAL(10, 2) NOT NULL,
    usuario_id INT REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE categorias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    usuario_id INT REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE transacoes (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    data DATE NOT NULL,
    categoria_id INT REFERENCES categorias(id) ON DELETE CASCADE,
    conta_id INT REFERENCES contas(id) ON DELETE CASCADE,
    usuario_id INT REFERENCES usuarios(id) ON DELETE CASCADE
);

SELECT * FROM CATEGORIAS
SELECT * FROM CONTAS
SELECT * FROM TRANSACOES
SELECT * FROM USUARIOS


INSERT INTO usuarios (nome, email, senha) VALUES
('João Silva', 'teste1', '123'),
('Maria Oliveira', 'maria.oliveira@example.com', '123'),
('Carlos Pereira', 'carlos.pereira@example.com', '123');

INSERT INTO contas (nome, saldo, usuario_id) VALUES 
('Conta Corrente', 1500.00, 1),  -- João Silva
('Conta Poupança', 3000.00, 1),  -- João Silva
('Conta Salário', 2000.00, 2),    -- Maria Oliveira
('Conta Salário', 2000.00, 3);    -- pedro

INSERT INTO categorias (nome, tipo, usuario_id) VALUES 
('Alimentação', 'Despesa', 1),     -- João Silva
('Transporte', 'Despesa', 1),      -- João Silva
('Salário', 'Receita', 1),         -- João Silva
('Lazer', 'Despesa', 2),           -- Maria Oliveira
('Educação', 'Despesa', 2);         -- Maria Oliveira

INSERT INTO transacoes (descricao, valor, data, categoria_id, conta_id, usuario_id) VALUES 
('Compra de supermercado', 200.00, '2023-10-01', 1, 1, 1),  -- João Silva
('Passagem de ônibus', 15.00, '2023-10-02', 2, 1, 1),      -- João Silva
('Salário do mês', 3000.00, '2023-10-05', 3, 2, 1),        -- João Silva
('Cinema com amigos', 50.00, '2023-10-06', 4, 2, 2),       -- Maria Oliveira
('Curso de inglês', 500.00, '2023-10-07', 5, 2, 2);        -- Maria Oliveira

