-- CREATE DATABASE conatesbd;
DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS sintomas CASCADE;
DROP TABLE IF EXISTS sintomasQueixa CASCADE;
DROP TABLE IF EXISTS consulta CASCADE;
DROP TABLE IF EXISTS paciente CASCADE;
DROP TABLE IF EXISTS enfermeiro CASCADE;
DROP TABLE IF EXISTS receita CASCADE;
DROP TABLE IF EXISTS fornecedores CASCADE;
DROP TABLE IF EXISTS medicamento CASCADE;
DROP TABLE IF EXISTS estoque CASCADE;
DROP TABLE IF EXISTS categoriaMedicamento CASCADE;
DROP TABLE IF EXISTS tipoMovimentacao CASCADE;
DROP TABLE IF EXISTS fornecedorMedicamento CASCADE;
DROP TABLE IF EXISTS loteFornecimento CASCADE;

CREATE TABLE usuario (
    cod_usuario text CONSTRAINT usuario_pkey PRIMARY KEY,
    tipo_usuario text,
    senha text,
    nome text
);
CREATE TABLE sintomas(
    cod_queixa bigserial CONSTRAINT queixa_pkey PRIMARY KEY,
    des_queixa VARCHAR(80) NOT NULL   
);
CREATE TABLE sintomasQueixa(
    cod_sintoma_queixa bigserial CONSTRAINT sintomaQueixa_pkey PRIMARY KEY,
    cod_consulta_id bigint NOT NULL,
    cod_paciente_id bigint NOT NULL,
    cod_queixa_id bigint NOT NULL
);
CREATE TABLE consulta (
    cod_consulta bigserial CONSTRAINT consulta_pkey PRIMARY KEY,
    cod_paciente_id bigint NOT NULL,
    dat_consulta date NOT NULL,
    hr_consulta int NOT NULL,
    cod_enf_id bigint NOT NULL 
);
CREATE TABLE paciente(
    cod_paciente bigint CONSTRAINT paciente_pkey PRIMARY KEY,
    nom_paciente VARCHAR(80) NOT NULL,
    idt_servidor_aluno CHAR(1) NOT NULL    
);

CREATE TABLE enfermeiro(
    cod_enf bigint CONSTRAINT enfermeiro_pkey PRIMARY KEY,
    nom_enf VARCHAR(80) NOT NULL   
);

CREATE TABLE receita(
    cod_receita bigserial CONSTRAINT receita_pkey PRIMARY KEY,
    cod_cons_id bigint NOT NULL ,
    cod_pac_id bigint NOT NULL,
    cod_medic_id bigint,
    txt_dosagem text,
    qtd_fornecida DECIMAL(7,2)
);
----------------------------------------------------
CREATE TABLE tipoMovimentacao(
    cod_tipo bigint CONSTRAINT tipoMov_pkey PRIMARY KEY,
    des_tipo varchar (30) NOT NULL 
   
);
CREATE TABLE loteFornecimento(
    cod_lote bigint CONSTRAINT lotefornecimento_pkey PRIMARY KEY,
    dat_recebimento date NOT NULL ,
    cnpj_empresa_id bigint NOT NULL
);
CREATE TABLE fornecedor(
    cnpj_empresa bigint CONSTRAINT cnpj_pkey PRIMARY KEY,
    nom_fantasia varchar(60) NOT NULL,
    nom_empresa varchar(60) NOT NULL,
    tel_empresa char(13) NOT NULL,
    email_empresa char(25) NOT NULL
);
CREATE TABLE fornecedorMedicamento(
    cod_fornecimento bigserial CONSTRAINT fornecimento_pkey PRIMARY KEY,
    cod_medic_id bigint NOT NULL ,
    cnpj_empresa_id bigint NOT NULL,
    qtd_medicamento bigint NOT NULL,
    txt_validacao text NOT NULL,
    est_movimentacao text NOT NULL
);


CREATE TABLE medicamento(
    cod_medic bigint CONSTRAINT medicamento_pkey PRIMARY KEY,
    nom_medic VARCHAR(80) NOT NULL,
    blb_foto bytea NOT NULL, 
    cod_tipo_id bigint NOT NULL
);
CREATE TABLE categoriaMedicamento(
    cod_tipo bigint CONSTRAINT categoria_pkey PRIMARY KEY,
    nom_tipo varchar (80) NOT NULL 
   
);


CREATE TABLE estoque (
    cod_estoque bigserial CONSTRAINT estoque_pkey PRIMARY KEY,
    cod_lote_id bigint NOT NULL,
    cod_medic_id bigint NOT NULL,
    cod_tipo_id bigint NOT NULL,
    dat_validade date NOT NULL,
    dat_movimentacao date NOT NULL,
    qtd_movimentada DECIMAL(7,2) NOT NULL
);

--Chaves de Estoque 
ALTER TABLE estoque ADD CONSTRAINT fk_estoque_medicamento FOREIGN KEY (cod_medic_id) 
    REFERENCES medicamento (cod_medic) MATCH SIMPLE
     ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE estoque ADD CONSTRAINT fk_estoque_medic FOREIGN KEY (cod_lote_id) 
    REFERENCES loteFornecimento (cod_lote) MATCH SIMPLE
     ON UPDATE RESTRICT ON DELETE RESTRICT;


ALTER TABLE estoque ADD CONSTRAINT fk_estoque_tipoMovimentacao FOREIGN KEY (cod_tipo_id) 
    REFERENCES tipoMovimentacao (cod_tipo) MATCH SIMPLE
     ON UPDATE RESTRICT ON DELETE RESTRICT;

--Chaves de medicamento
    ALTER TABLE medicamento ADD CONSTRAINT fk_medicamento_categoria FOREIGN KEY (cod_tipo_id) 
    REFERENCES categoriaMedicamento(cod_tipo) MATCH SIMPLE
     ON UPDATE RESTRICT ON DELETE RESTRICT; 
     
--Chaves de Consulta

ALTER TABLE consulta ADD CONSTRAINT fk_consulta_paciente FOREIGN KEY (cod_paciente_id) 
      REFERENCES paciente (cod_paciente) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE consulta ADD CONSTRAINT fk_consulta_enfermeiro FOREIGN KEY (cod_enf_id) 
      REFERENCES enfermeiro (cod_enf) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

-- Chaves de sintomasQueixa
ALTER TABLE sintomasQueixa ADD CONSTRAINT fk_sintomasQueixa_consulta FOREIGN KEY (cod_consulta_id) 
      REFERENCES consulta (cod_consulta) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE sintomasQueixa ADD CONSTRAINT fk_sintomasQueixa_paciente FOREIGN KEY (cod_paciente_id) 
      REFERENCES paciente (cod_paciente) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE sintomasQueixa ADD CONSTRAINT fk_sintomasQueixa_queixa FOREIGN KEY (cod_queixa_id) 
      REFERENCES sintomas (cod_queixa) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

--Chaves de Receita
ALTER TABLE receita ADD CONSTRAINT fk_receita_consulta FOREIGN KEY (cod_cons_id) 
      REFERENCES consulta (cod_consulta) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE receita ADD CONSTRAINT fk_receita_paciente FOREIGN KEY (cod_pac_id) 
      REFERENCES paciente (cod_paciente) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE receita ADD CONSTRAINT fk_receita_medicamento FOREIGN KEY (cod_medic_id) 
      REFERENCES medicamento (cod_medic) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;


--Chaves de loteFornecimento
    ALTER TABLE loteFornecimento ADD CONSTRAINT fk_lote_fornecedores FOREIGN KEY (cnpj_empresa_id) 
      REFERENCES fornecedor(cnpj_empresa) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;
      
--Chaves de FornecedorMedicamento 
ALTER TABLE fornecedorMedicamento ADD CONSTRAINT fk_fornecimento_medicamento FOREIGN KEY (cod_medic_id) 
      REFERENCES medicamento (cod_medic) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE fornecedorMedicamento ADD CONSTRAINT fk_fornecimento_fornecedor FOREIGN KEY (cnpj_empresa_id) 
      REFERENCES fornecedor (cnpj_empresa) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

---Enfermeiros cadastrados 
INSERT INTO enfermeiro(cod_enf, nom_enf) VALUES (01, 'Sofia Monteiro');
INSERT INTO enfermeiro(cod_enf, nom_enf) VALUES (02, 'Gleyser Santos');
INSERT INTO enfermeiro(cod_enf, nom_enf) VALUES (03, 'Debora Barros');
INSERT INTO enfermeiro(cod_enf, nom_enf) VALUES (04, 'Lucas Haddad');
INSERT INTO enfermeiro(cod_enf, nom_enf) VALUES (05, 'Thiago Cabral');

----Usuarios cadastrados 
INSERT INTO usuario(cod_usuario, tipo_usuario, senha, nome) VALUES ('201411101', 'RECEPCIONISTA', md5('123456'), 'Patrícia Silva');
INSERT INTO usuario(cod_usuario, tipo_usuario, senha, nome) VALUES ('201411102', 'RECEPCIONISTA', md5('1234567'), 'Laura Cardoso');
INSERT INTO usuario(cod_usuario, tipo_usuario, senha, nome) VALUES ('201411103', 'RECEPCIONISTA', md5('12345'), 'Augusto Santos');

INSERT INTO usuario(cod_usuario, tipo_usuario, senha, nome) VALUES ('201411120', 'FARMACEUTICO', md5('54123'), 'Maria Fernanda');
INSERT INTO usuario(cod_usuario, tipo_usuario, senha, nome) VALUES ('201411121', 'FARMACEUTICO', md5('987456'), 'Deborah Rocha');
INSERT INTO usuario(cod_usuario, tipo_usuario, senha, nome) VALUES ('201411122', 'FARMACEUTICO', md5('12456'), 'Ronaldo Junio');

INSERT INTO usuario(cod_usuario, tipo_usuario, senha, nome) VALUES ('201411130', 'ENFERMEIRO', md5('147258'), 'Nathália Machado');
INSERT INTO usuario(cod_usuario, tipo_usuario, senha, nome) VALUES ('201411131', 'ENFERMEIRO', md5('963258'), 'Victória Thainá');
INSERT INTO usuario(cod_usuario, tipo_usuario, senha, nome) VALUES ('201411132', 'ENFERMEIRO', md5('852147'), 'João Pedro');


--- Inserção pacientes 

INSERT INTO paciente(cod_paciente, nom_paciente, idt_servidor_aluno) VALUES (201411130227, 'Lorena Nunes Silva', 'A');
INSERT INTO paciente(cod_paciente, nom_paciente, idt_servidor_aluno) VALUES (201411130189, 'Bruna Chaves Carneiro', 'A');
INSERT INTO paciente(cod_paciente, nom_paciente, idt_servidor_aluno) VALUES (201411130260, 'Thaynara Carolino Ferreira', 'A');
INSERT INTO paciente(cod_paciente, nom_paciente, idt_servidor_aluno) VALUES (201411130316, 'Thayla Gomes Montaldi', 'A');

--
--- Inserção sintomas
INSERT INTO sintomas(cod_queixa, des_queixa) VALUES ('1', 'Dores musculares');
INSERT INTO sintomas(cod_queixa, des_queixa) VALUES ('2', 'Cefaleia');
INSERT INTO sintomas(cod_queixa, des_queixa) VALUES ('3', 'Dores Estomacais');
INSERT INTO sintomas(cod_queixa, des_queixa) VALUES ('4', 'Hematomas');
INSERT INTO sintomas(cod_queixa, des_queixa) VALUES ('5', 'Dores Abdominais');

----Inserção de Categoria dos Medicamentos
INSERT INTO categoriaMedicamento (cod_tipo, nom_tipo) VALUES (1, 'Medicamento para região muscular');
INSERT INTO categoriaMedicamento (cod_tipo, nom_tipo) VALUES (2, 'Medicamento para região encefálica');
INSERT INTO categoriaMedicamento (cod_tipo, nom_tipo) VALUES (3, 'Medicamento para região estomacal');
INSERT INTO categoriaMedicamento (cod_tipo, nom_tipo) VALUES (4, 'Medicamento para hematomas ');
INSERT INTO categoriaMedicamento (cod_tipo, nom_tipo) VALUES (5, 'Medicamento para região abdominal');

----Inserção de Fornecedores
INSERT INTO fornecedor(cnpj_empresa, nom_fantasia, nom_empresa, tel_empresa, email_empresa) VALUES (21759758000188,'FARMAX','FARMAX','(37)2101-9696','farmax@yahoo.com');
INSERT INTO fornecedor(cnpj_empresa, nom_fantasia, nom_empresa, tel_empresa, email_empresa) VALUES (29785870000367,'Neo Química','Laboratório Neoquímica Comércio e Indústria Ltda','(62)3310-2500','neoquimica@hotmail.com');

----Inserção de Tipo de movimentação

INSERT INTO tipoMovimentacao(cod_tipo, des_tipo)VALUES(1,'Entrada de Medicamentos');
INSERT INTO tipoMovimentacao(cod_tipo, des_tipo)VALUES(2,'Retirada de Medicamento');
INSERT INTO tipoMovimentacao(cod_tipo, des_tipo)VALUES(3,'Descarte de Medicamentos');