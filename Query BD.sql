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


DROP TABLE CATEGORIAS 
DROP TABLE CONTAS
DROP TABLE TRANSACOES	
DROP TABLE USUARIOS


SELECT * FROM CATEGORIAS
SELECT * FROM CONTAS
SELECT * FROM TRANSACOES
SELECT * FROM USUARIOS


-- Inserindo usuários
INSERT INTO usuarios (nome, email, senha) VALUES
('João Silva', 'teste', '123'),
('Maria Souza', 'maria@email.com', 'senha456'),
('Carlos Oliveira', 'carlos@email.com', 'senha789');

-- Inserindo contas
INSERT INTO contas (nome, saldo, usuario_id) VALUES
('Conta Corrente', 2500.00, 1),
('Poupança', 5000.00, 1),
('Conta Corrente', 1500.00, 2),
('Investimentos', 10000.00, 2),
('Conta Corrente', 800.00, 3);

-- Inserindo categorias
INSERT INTO categorias (nome, tipo, usuario_id) VALUES
('Salário', 'Receita', 1),
('Alimentação', 'Despesa', 1),
('Lazer', 'Despesa', 1),
('Transporte', 'Despesa', 1),
('Investimentos', 'Receita', 2),
('Educação', 'Despesa', 2),
('Saúde', 'Despesa', 2),
('Freelance', 'Receita', 3),
('Moradia', 'Despesa', 3);

-- Inserindo transações
INSERT INTO transacoes (descricao, valor, data, categoria_id, conta_id, usuario_id) VALUES
-- Usuário 1
('Recebimento de salário', 3000.00, '2025-05-01', 1, 1, 1),
('Supermercado', -250.50, '2025-05-03', 2, 1, 1),
('Cinema', -60.00, '2025-05-05', 3, 1, 1),
('Uber', -35.00, '2025-05-06', 4, 1, 1),
('Transferência para Poupança', -500.00, '2025-05-07', 1, 1, 1),
('Recebimento na Poupança', 500.00, '2025-05-07', 1, 2, 1),

-- Usuário 2
('Investimento em ações', 2000.00, '2025-05-02', 5, 4, 2),
('Mensalidade Faculdade', -800.00, '2025-05-05', 6, 3, 2),
('Consulta médica', -250.00, '2025-05-08', 7, 3, 2),

-- Usuário 3
('Trabalho Freelance', 1200.00, '2025-05-01', 8, 5, 3),
('Aluguel', -700.00, '2025-05-03', 9, 5, 3);




-- Novas contas
INSERT INTO contas (nome, saldo, usuario_id) VALUES
('Cartão de Crédito', -500.00, 1),
('Cartão de Crédito', -1200.00, 2),
('Poupança', 2000.00, 3);

-- Novas categorias
INSERT INTO categorias (nome, tipo, usuario_id) VALUES
('Roupas', 'Despesa', 1),
('Viagem', 'Despesa', 1),
('Bônus', 'Receita', 2),
('Presentes', 'Despesa', 2),
('Reembolso', 'Receita', 3),
('Pets', 'Despesa', 3);

-- Novas transações para usuário 1
INSERT INTO transacoes (descricao, valor, data, categoria_id, conta_id, usuario_id) VALUES
('Compra de roupas', -300.00, '2025-05-10', 10, 1, 1),
('Passagem para viagem', -1500.00, '2025-05-12', 11, 1, 1),
('Hospedagem viagem', -2000.00, '2025-05-13', 11, 2, 1),
('Pagamento fatura cartão', -500.00, '2025-05-15', 10, 6, 1),
('Cashback do cartão', 50.00, '2025-05-16', 1, 6, 1),

-- Novas transações para usuário 2
('Bônus de desempenho', 3000.00, '2025-05-10', 12, 4, 2),
('Compra de presentes', -400.00, '2025-05-11', 13, 3, 2),
('Jantar especial', -350.00, '2025-05-11', 3, 3, 2),
('Pagamento fatura cartão', -1200.00, '2025-05-15', 13, 7, 2),

-- Novas transações para usuário 3
('Reembolso transporte', 150.00, '2025-05-10', 14, 5, 3),
('Consulta veterinária', -200.00, '2025-05-12', 15, 5, 3),
('Pet shop', -120.00, '2025-05-13', 15, 8, 3),
('Compra de ração', -80.00, '2025-05-13', 15, 8, 3),
('Depósito na poupança', -500.00, '2025-05-14', 8, 5, 3),
('Recebimento na poupança', 500.00, '2025-05-14', 8, 8, 3);

INSERT INTO usuarios (nome, email, senha) VALUES
('Ana Pereira', 'ana@email.com', 'senha321'),
('Lucas Fernandes', 'lucas@email.com', 'senha654'),
('Beatriz Lima', 'beatriz@email.com', 'senha987');

INSERT INTO contas (nome, saldo, usuario_id) VALUES
('Conta Corrente', 4500.00, 4),
('Poupança', 8000.00, 4),
('Conta Corrente', 3200.00, 5),
('Investimentos', 15000.00, 5),
('Conta Corrente', 2700.00, 6),
('Cartão de Crédito', -700.00, 6);

INSERT INTO categorias (nome, tipo, usuario_id) VALUES
('Salário', 'Receita', 4),
('Alimentação', 'Despesa', 4),
('Lazer', 'Despesa', 4),
('Educação', 'Despesa', 4),
('Freelance', 'Receita', 5),
('Moradia', 'Despesa', 5),
('Saúde', 'Despesa', 5),
('Transporte', 'Despesa', 5),
('Rendimentos', 'Receita', 6),
('Pets', 'Despesa', 6),
('Compras', 'Despesa', 6);

-- Usuário 4 - Ana Pereira
INSERT INTO transacoes (descricao, valor, data, categoria_id, conta_id, usuario_id) VALUES
('Salário mensal', 5000.00, '2025-05-01', 16, 9, 4),
('Supermercado', -600.00, '2025-05-03', 17, 9, 4),
('Cinema com amigos', -80.00, '2025-05-06', 18, 9, 4),
('Curso online', -300.00, '2025-05-07', 19, 9, 4),
('Depósito na poupança', -1000.00, '2025-05-08', 16, 9, 4),
('Recebimento na poupança', 1000.00, '2025-05-08', 16, 10, 4);

-- Usuário 5 - Lucas Fernandes
INSERT INTO transacoes (descricao, valor, data, categoria_id, conta_id, usuario_id) VALUES
('Trabalho freelance', 2500.00, '2025-05-02', 20, 11, 5),
('Aluguel apartamento', -1200.00, '2025-05-05', 21, 11, 5),
('Consulta médica', -400.00, '2025-05-07', 22, 11, 5),
('Gasolina', -250.00, '2025-05-09', 23, 11, 5),
('Investimento em ações', -3000.00, '2025-05-10', 20, 11, 5),
('Rendimento de ações', 500.00, '2025-05-15', 24, 12, 5);

-- Usuário 6 - Beatriz Lima
INSERT INTO transacoes (descricao, valor, data, categoria_id, conta_id, usuario_id) VALUES
('Rendimento CDB', 800.00, '2025-05-01', 24, 13, 6),
('Consulta veterinária', -250.00, '2025-05-03', 25, 13, 6),
('Pet shop', -180.00, '2025-05-04', 25, 13, 6),
('Compra online', -600.00, '2025-05-06', 26, 13, 6),
('Pagamento cartão', -700.00, '2025-05-10', 26, 14, 6),
('Depósito na conta', 2000.00, '2025-05-12', 24, 13, 6);
