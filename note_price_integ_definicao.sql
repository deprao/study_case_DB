CREATE DATABASE notebook_store_integ;

CREATE TABLE Loja_cadastrada(
	Nome text,
	Website text,
	PRIMARY KEY(nome)
);

CREATE TABLE Notebook_gamer(
	id SERIAL PRIMARY KEY,
	Modelo text,
	Sist_Op text,
	Memo_RAM text,
	Placa_video text,
	Processador text,
	Armazena_HD text,
	Armazena_SSD text,
	preco text,
	marca text
);

CREATE TABLE NG_Coletado(
	Loja_retirada text,
	Data_coleta text,
)INHERITS(Notebook_gamer);

CREATE TABLE Avaliacoes(
	Modelo text,
	Nota text,
	Comentários text[],
	Avaliadores text[],
);

CREATE TABLE Crawlers(
	id SERIAL PRIMARY KEY,
	Versao text,
	Data_inclusao text,
	Data_Alteracao text,
	Loja_coleta text,
);
