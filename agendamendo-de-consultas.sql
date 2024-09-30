CREATE TABLE cidade (
  id_cidade INTEGER NOT NULL,
  nome VARCHAR(100) NOT NULL,
  UF CHAR(2) NOT NULL,
  CONSTRAINT pkCidade PRIMARY KEY (id_cidade)
);

CREATE TABLE endereco (
  id_endereco INTEGER NOT NULL,
  logradouro VARCHAR(100),
  bairro VARCHAR(100),
  numero VARCHAR NOT NULL,
  complemento VARCHAR(50),
  CEP VARCHAR(9) NOT NULL,
  id_cidade INTEGER NOT NULL,
  CONSTRAINT pkEndereco PRIMARY KEY (id_endereco),
  CONSTRAINT fkEnderecoCidade FOREIGN KEY (id_cidade)
    REFERENCES cidade (id_cidade)
);

CREATE TABLE pessoa (
  id_pessoa INTEGER NOT NULL,
  nome VARCHAR(100) NOT NULL,
  CPF VARCHAR(15) NOT NULL,
  data_nascimento DATE NOT NULL,
  RG VARCHAR(15),
  Exped_RG DATE,
  email VARCHAR(100) NOT NULL,
  telefone VARCHAR(20) NOT NULL,
  id_endereco INTEGER NOT NULL,
  CONSTRAINT pkPessoa PRIMARY KEY (id_pessoa),
  CONSTRAINT fkPessoaEndereco FOREIGN KEY (id_endereco)
    REFERENCES endereco (id_endereco)
);

CREATE TABLE medico (
  id_medico INTEGER NOT NULL,
  CRM VARCHAR(10) NOT NULL,
  id_pessoa INTEGER NOT NULL,
  CONSTRAINT pkMedico PRIMARY KEY (id_medico),
  CONSTRAINT fkMedicoPessoa FOREIGN KEY (id_pessoa)
    REFERENCES pessoa (id_pessoa)
);

CREATE TABLE especialidade (
  id_especialidade INTEGER NOT NULL,
  nome VARCHAR(100) NOT NULL,
  CONSTRAINT pkEspecialidade PRIMARY KEY (id_especialidade)
);

CREATE TABLE medico_especialidade (
  id_especialidade INTEGER NOT NULL,
  id_medico INTEGER NOT NULL,
  CONSTRAINT pkMedicoEspecialidade PRIMARY KEY (id_medico, id_especialidade),
  CONSTRAINT fkMEMedico FOREIGN KEY (id_medico)
    REFERENCES medico (id_medico),
  CONSTRAINT fkMEEspecialidade FOREIGN KEY (id_especialidade)
    REFERENCES especialidade (id_especialidade)
);

CREATE TABLE ubs (
  id_ubs INTEGER NOT NULL,
  nome VARCHAR(100) NOT NULL,
  id_endereco INTEGER NOT NULL,
  CONSTRAINT pkubs PRIMARY KEY (id_ubs),
  CONSTRAINT fkubsEndereco FOREIGN KEY (id_endereco)
    REFERENCES endereco (id_endereco)
);

CREATE TABLE contrato (
  id_contrato INTEGER NOT NULL,
  data DATE NOT NULL,
  data_inicio DATE NOT NULL,
  data_termino DATE NOT NULL,
  carga_horaria INTEGER NOT NULL,
  id_medico INTEGER NOT NULL,
  id_especialidade INTEGER NOT NULL,
  id_ubs INTEGER NOT NULL,
  CONSTRAINT pkContrato PRIMARY KEY (id_contrato),
  CONSTRAINT fkContratoMedico FOREIGN KEY (id_medico)
    REFERENCES medico (id_medico),
  CONSTRAINT fkContratoEspecialidade FOREIGN KEY (id_especialidade)
    REFERENCES especialidade (id_especialidade),
  CONSTRAINT fkContratoUbs FOREIGN KEY (id_ubs)
    REFERENCES ubs (id_ubs)
);

CREATE TABLE paciente (
  id_paciente INTEGER NOT NULL,
  numero_sus VARCHAR(15) NOT NULL,
  id_pessoa INTEGER NOT NULL,
  CONSTRAINT pkPaciente PRIMARY KEY (id_paciente),
  CONSTRAINT fkPacientePessoa FOREIGN KEY (id_pessoa)
    REFERENCES pessoa (id_pessoa)
);

CREATE TABLE consulta (
  id_consulta INTEGER NOT NULL,
  data DATE NOT NULL,
  hora TIME NOT NULL,
  status VARCHAR(30) NOT NULL,
  id_paciente INTEGER NOT NULL,
  id_contrato INTEGER NOT NULL,
  CONSTRAINT pkConsulta PRIMARY KEY (id_consulta),
  CONSTRAINT fkConsultaPaciente FOREIGN KEY (id_paciente)
    REFERENCES paciente (id_paciente),
  CONSTRAINT fkConsultaContrato FOREIGN KEY (id_contrato)
    REFERENCES contrato (id_contrato)
);

CREATE TABLE pedido_exame (
  id_pedido_exame INTEGER NOT NULL,
  data DATE NOT NULL,
  requisicao VARCHAR(300) NOT NULL,
  id_consulta INTEGER NOT NULL,
  id_resultado INTEGER NOT NULL,
  CONSTRAINT pkPedidoExame PRIMARY KEY (id_pedido_exame),
  CONSTRAINT fkPedidoExameConsulta FOREIGN KEY (id_consulta)
    REFERENCES consulta (id_consulta)
);

CREATE TABLE resultado (
  id_resultado INTEGER NOT NULL,
  obsevacoes VARCHAR(300) NOT NULL,
  CONSTRAINT pkResultado PRIMARY KEY (id_resultado),

);


CREATE TABLE prescricao (
  id_prescricao INTEGER NOT NULL,
  descricao VARCHAR(300) NOT NULL,
  data DATE NOT NULL,
  id_consulta INTEGER NOT NULL,
  CONSTRAINT pkPrescricao PRIMARY KEY (id_prescricao),
  CONSTRAINT fkPrescricaoConsulta FOREIGN KEY (id_consulta)
    REFERENCES consulta (id_consulta)
);

ALTER TABLE resultado
ADD COLUMN id_pedido_exame INTEGER, ADD CONSTRAINT fk_id_pedido_exame_resultado FOREIGN KEY (id_pedido_exame) REFERENCES pedido_exame (id_pedido_exame);
