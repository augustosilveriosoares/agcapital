CREATE TABLE status (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    descricao VARCHAR(255) NOT NULL UNIQUE
);

-- Inserção dos dados (status) conforme definidos nas constantes
INSERT INTO status (descricao) VALUES ('Em Andamento');
INSERT INTO status (descricao) VALUES ('Finalizado');
INSERT INTO status (descricao) VALUES ('Cancelado');
INSERT INTO status (descricao) VALUES ('Aguardando');

-- Tabela Cliente
CREATE TABLE cliente (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

-- Tabela Projeto
CREATE TABLE projeto (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255) NOT NULL,
    status_id UUID NOT NULL,
    cliente_id UUID NOT NULL,
    FOREIGN KEY (status_id) REFERENCES status(id) ON DELETE CASCADE,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE
);

-- Tabela Atividade
CREATE TABLE atividade (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    descricao VARCHAR(255) NOT NULL,
    status_id UUID NOT NULL,
    projeto_id UUID NOT NULL,
    FOREIGN KEY (status_id) REFERENCES status(id) ON DELETE CASCADE,
    FOREIGN KEY (projeto_id) REFERENCES projeto(id) ON DELETE CASCADE
);

-- Inserir Clientes
INSERT INTO cliente (nome, email) VALUES
('João Silva', 'joao.silva@example.com'),
('Maria Oliveira', 'maria.oliveira@example.com'),
('Carlos Souza', 'carlos.souza@example.com'),
('Ana Costa', 'ana.costa@example.com'),
('Pedro Santos', 'pedro.santos@example.com'),
('Laura Almeida', 'laura.almeida@example.com'),
('Lucas Pereira', 'lucas.pereira@example.com'),
('Mariana Ferreira', 'mariana.ferreira@example.com'),
('Fernanda Gomes', 'fernanda.gomes@example.com'),
('Ricardo Martins', 'ricardo.martins@example.com'),
('Rafael Lima', 'rafael.lima@example.com'),
('Gabriela Silva', 'gabriela.silva@example.com'),
('Bruno Rocha', 'bruno.rocha@example.com'),
('Juliana Cruz', 'juliana.cruz@example.com'),
('Felipe Ribeiro', 'felipe.ribeiro@example.com'),
('Patrícia Mendes', 'patricia.mendes@example.com'),
('Gustavo Costa', 'gustavo.costa@example.com'),
('Sofia Pereira', 'sofia.pereira@example.com'),
('Júlia Alves', 'julia.alves@example.com'),
('Renato Carvalho', 'renato.carvalho@example.com');

-- Inserir Projetos para cada Cliente

INSERT INTO projeto (nome, status_id, cliente_id) VALUES
('Desenvolvimento do Sistema de CRM', (SELECT id FROM status WHERE descricao = 'Em Andamento' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'João Silva' LIMIT 1)),
('Reformulação do Site Institucional', (SELECT id FROM status WHERE descricao = 'Finalizado' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Maria Oliveira' LIMIT 1)),
('Implantação do Sistema ERP', (SELECT id FROM status WHERE descricao = 'Cancelado' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Carlos Souza' LIMIT 1)),
('Desenvolvimento de Nova Funcionalidade', (SELECT id FROM status WHERE descricao = 'Aguardando' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Ana Costa' LIMIT 1)),
('Migração para a Nuvem', (SELECT id FROM status WHERE descricao = 'Em Andamento' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Pedro Santos' LIMIT 1)),
('Atualização do Banco de Dados', (SELECT id FROM status WHERE descricao = 'Finalizado' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Laura Almeida' LIMIT 1)),
('Suporte Técnico ao Cliente', (SELECT id FROM status WHERE descricao = 'Cancelado' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Lucas Pereira' LIMIT 1)),
('Desenvolvimento de Aplicativo Mobile', (SELECT id FROM status WHERE descricao = 'Aguardando' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Mariana Ferreira' LIMIT 1)),
('Implementação de Sistema de Pagamentos', (SELECT id FROM status WHERE descricao = 'Em Andamento' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Fernanda Gomes' LIMIT 1)),
('Criação de Dashboard de Vendas', (SELECT id FROM status WHERE descricao = 'Finalizado' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Ricardo Martins' LIMIT 1)),
('Customização de Ferramenta de Marketing', (SELECT id FROM status WHERE descricao = 'Cancelado' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Rafael Lima' LIMIT 1)),
('Desenvolvimento de Sistema de Monitoramento', (SELECT id FROM status WHERE descricao = 'Aguardando' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Gabriela Silva' LIMIT 1)),
('Consultoria em Segurança da Informação', (SELECT id FROM status WHERE descricao = 'Em Andamento' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Bruno Rocha' LIMIT 1)),
('Integração de Sistemas de Logística', (SELECT id FROM status WHERE descricao = 'Finalizado' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Juliana Cruz' LIMIT 1)),
('Ajustes no Sistema de Suporte ao Cliente', (SELECT id FROM status WHERE descricao = 'Cancelado' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Felipe Ribeiro' LIMIT 1)),
('Atualização de Plataforma de E-commerce', (SELECT id FROM status WHERE descricao = 'Aguardando' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Patrícia Mendes' LIMIT 1)),
('Automatização de Processos Internos', (SELECT id FROM status WHERE descricao = 'Em Andamento' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Gustavo Costa' LIMIT 1)),
('Desenvolvimento de Ferramenta de Análise de Dados', (SELECT id FROM status WHERE descricao = 'Finalizado' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Sofia Pereira' LIMIT 1)),
('Implementação de Sistema de Gestão de Projetos', (SELECT id FROM status WHERE descricao = 'Cancelado' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Júlia Alves' LIMIT 1)),
('Criação de Sistema de Feedback de Clientes', (SELECT id FROM status WHERE descricao = 'Aguardando' LIMIT 1), (SELECT id FROM cliente WHERE nome = 'Renato Carvalho' LIMIT 1));



-- Inserir Atividades para cada Projeto
INSERT INTO atividade (descricao, status_id, projeto_id) VALUES
('Planejamento de Funcionalidades', (SELECT id FROM status WHERE descricao = 'EM_ANDAMENTO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Desenvolvimento do Sistema de CRM' LIMIT 1)),
('Finalização de Design do Website', (SELECT id FROM status WHERE descricao = 'FINALIZADO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Reformulação do Site Institucional' LIMIT 1)),
('Análise de Sistemas Obsoletos', (SELECT id FROM status WHERE descricao = 'CANCELADO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Implantação do Sistema ERP' LIMIT 1)),
('Especificação de Requisitos', (SELECT id FROM status WHERE descricao = 'AGUARDANDO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Desenvolvimento de Nova Funcionalidade' LIMIT 1)),
('Migrar Banco de Dados para Nuvem', (SELECT id FROM status WHERE descricao = 'EM_ANDAMENTO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Migração para a Nuvem' LIMIT 1)),
('Testes de Integração no Banco de Dados', (SELECT id FROM status WHERE descricao = 'FINALIZADO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Atualização do Banco de Dados' LIMIT 1)),
('Encerramento do Suporte Técnico', (SELECT id FROM status WHERE descricao = 'CANCELADO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Suporte Técnico ao Cliente' LIMIT 1)),
('Desenvolvimento de Interface do App', (SELECT id FROM status WHERE descricao = 'AGUARDANDO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Desenvolvimento de Aplicativo Mobile' LIMIT 1)),
('Integração de Sistemas de Pagamento', (SELECT id FROM status WHERE descricao = 'EM_ANDAMENTO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Implementação de Sistema de Pagamentos' LIMIT 1)),
('Criação de Relatórios de Vendas', (SELECT id FROM status WHERE descricao = 'FINALIZADO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Criação de Dashboard de Vendas' LIMIT 1)),
('Ajustes de Layout de Ferramenta de Marketing', (SELECT id FROM status WHERE descricao = 'CANCELADO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Customização de Ferramenta de Marketing' LIMIT 1)),
('Segurança no Sistema de Monitoramento', (SELECT id FROM status WHERE descricao = 'AGUARDANDO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Desenvolvimento de Sistema de Monitoramento' LIMIT 1)),
('Treinamento de Equipe de TI em Segurança', (SELECT id FROM status WHERE descricao = 'EM_ANDAMENTO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Consultoria em Segurança da Informação' LIMIT 1)),
('Desenvolvimento de Integração de Logística', (SELECT id FROM status WHERE descricao = 'FINALIZADO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Integração de Sistemas de Logística' LIMIT 1)),
('Melhorias no Sistema de Suporte ao Cliente', (SELECT id FROM status WHERE descricao = 'CANCELADO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Ajustes no Sistema de Suporte ao Cliente' LIMIT 1)),
('Testes no Sistema de E-commerce', (SELECT id FROM status WHERE descricao = 'AGUARDANDO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Atualização de Plataforma de E-commerce' LIMIT 1)),
('Automatização de Processos em Back-end', (SELECT id FROM status WHERE descricao = 'EM_ANDAMENTO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Automatização de Processos Internos' LIMIT 1)),
('Desenvolvimento de Algoritmos de Análise de Dados', (SELECT id FROM status WHERE descricao = 'FINALIZADO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Desenvolvimento de Ferramenta de Análise de Dados' LIMIT 1)),
('Criação de Sistema de Feedback do Cliente', (SELECT id FROM status WHERE descricao = 'CANCELADO' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Implementação de Sistema de Gestão de Projetos' LIMIT 1));.

INSERT INTO atividade (descricao, status_id, projeto_id) VALUES
('Planejamento de Funcionalidades', (SELECT id FROM status WHERE descricao = 'Em Andamento' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Desenvolvimento do Sistema de CRM' LIMIT 1)),
('Finalização de Design do Website', (SELECT id FROM status WHERE descricao = 'Finalizado' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Reformulação do Site Institucional' LIMIT 1)),
('Análise de Sistemas Obsoletos', (SELECT id FROM status WHERE descricao = 'Cancelado' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Implantação do Sistema ERP' LIMIT 1)),
('Especificação de Requisitos', (SELECT id FROM status WHERE descricao = 'Aguardando' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Desenvolvimento de Nova Funcionalidade' LIMIT 1)),
('Migrar Banco de Dados para Nuvem', (SELECT id FROM status WHERE descricao = 'Em Andamento' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Migração para a Nuvem' LIMIT 1)),
('Testes de Integração no Banco de Dados', (SELECT id FROM status WHERE descricao = 'Finalizado' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Atualização do Banco de Dados' LIMIT 1)),
('Encerramento do Suporte Técnico', (SELECT id FROM status WHERE descricao = 'Cancelado' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Suporte Técnico ao Cliente' LIMIT 1)),
('Desenvolvimento de Interface do App', (SELECT id FROM status WHERE descricao = 'Aguardando' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Desenvolvimento de Aplicativo Mobile' LIMIT 1)),
('Integração de Sistemas de Pagamento', (SELECT id FROM status WHERE descricao = 'Em Andamento' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Implementação de Sistema de Pagamentos' LIMIT 1)),
('Criação de Relatórios de Vendas', (SELECT id FROM status WHERE descricao = 'Finalizado' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Criação de Dashboard de Vendas' LIMIT 1)),
('Ajustes de Layout de Ferramenta de Marketing', (SELECT id FROM status WHERE descricao = 'Cancelado' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Customização de Ferramenta de Marketing' LIMIT 1)),
('Segurança no Sistema de Monitoramento', (SELECT id FROM status WHERE descricao = 'Aguardando' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Desenvolvimento de Sistema de Monitoramento' LIMIT 1)),
('Treinamento de Equipe de TI em Segurança', (SELECT id FROM status WHERE descricao = 'Em Andamento' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Consultoria em Segurança da Informação' LIMIT 1)),
('Desenvolvimento de Integração de Logística', (SELECT id FROM status WHERE descricao = 'Finalizado' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Integração de Sistemas de Logística' LIMIT 1)),
('Melhorias no Sistema de Suporte ao Cliente', (SELECT id FROM status WHERE descricao = 'Cancelado' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Ajustes no Sistema de Suporte ao Cliente' LIMIT 1)),
('Testes no Sistema de E-commerce', (SELECT id FROM status WHERE descricao = 'Aguardando' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Atualização de Plataforma de E-commerce' LIMIT 1)),
('Automatização de Processos em Back-end', (SELECT id FROM status WHERE descricao = 'Em Andamento' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Automatização de Processos Internos' LIMIT 1)),
('Desenvolvimento de Algoritmos de Análise de Dados', (SELECT id FROM status WHERE descricao = 'Finalizado' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Desenvolvimento de Ferramenta de Análise de Dados' LIMIT 1)),
('Criação de Sistema de Feedback do Cliente', (SELECT id FROM status WHERE descricao = 'Cancelado' LIMIT 1), (SELECT id FROM projeto WHERE nome = 'Implementação de Sistema de Gestão de Projetos' LIMIT 1));




