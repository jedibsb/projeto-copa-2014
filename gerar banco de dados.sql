-- Geração de Modelo físico
-- Sql ANSI 2003 - brModelo.



CREATE TABLE TIPOGOL (
codigo int PRIMARY KEY not null AUTO_INCREMENT,
tipo varchar (200) not null
);

CREATE TABLE JOGADOR (
codigo int(4) PRIMARY KEY not null AUTO_INCREMENT,
camisa int not null,
nome varchar (200) not null,
codigo_selecao int not null
);

CREATE TABLE ESTADIO (
codigo int PRIMARY KEY not null AUTO_INCREMENT,
nome varchar (200) not null,
cidade_estado varchar (200) not null
);

CREATE TABLE TIPOJOGO (
codigo int PRIMARY KEY not null AUTO_INCREMENT,
descricao varchar (200) not null
);

CREATE TABLE PENALTI (
codigo int PRIMARY KEY not null AUTO_INCREMENT,
codigo_jogo int not null,
codigo_jogador int not null,
descricao varchar (200) not null
);

CREATE TABLE JOGO (
codigo int PRIMARY KEY not null AUTO_INCREMENT,
codigo_s1 int not null,
codigo_s2 int not null,
codigo_estadio int not null,
tipo_jogo int not null,
data date not null,
FOREIGN KEY(codigo_estadio) REFERENCES ESTADIO (codigo),
FOREIGN KEY(tipo_jogo) REFERENCES TIPOJOGO (codigo)
);

CREATE TABLE SELECAO (
codigo int PRIMARY KEY not null AUTO_INCREMENT,
pais varchar (200) not null,
tecnico varchar (200)not null
);

CREATE TABLE GOL (
codigo int PRIMARY KEY not null AUTO_INCREMENT,
codigo_jogo int not null,
codigo_jogador int not null,
codigo_tipo int not null,
tempo int not null,
acrescimo int,
FOREIGN KEY(codigo_jogo) REFERENCES JOGO (codigo),
FOREIGN KEY(codigo_jogador) REFERENCES JOGADOR (codigo),
FOREIGN KEY(codigo_tipo) REFERENCES TIPOGOL (codigo)
);

ALTER TABLE JOGADOR ADD FOREIGN KEY(codigo_selecao) REFERENCES SELECAO (codigo);
ALTER TABLE PENALTI ADD FOREIGN KEY(codigo_jogo) REFERENCES JOGO (codigo);
ALTER TABLE PENALTI ADD FOREIGN KEY(codigo_jogador) REFERENCES JOGADOR (codigo);
ALTER TABLE JOGO ADD FOREIGN KEY(codigo_s1) REFERENCES SELECAO (codigo);
ALTER TABLE JOGO ADD FOREIGN KEY(codigo_s2) REFERENCES SELECAO (codigo);
