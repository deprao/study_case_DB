CREATE DATABASE notebook_store_integ;

CREATE TABLE Loja_cadastrada(
	Nome text,
	Website text,
	PRIMARY KEY(nome)
);

CREATE TABLE Notebook_gamer(
	Modelo text PRIMARY KEY,
	Sist_Op text,
	Memo_RAM text,
	Placa_video text,
	Processador text,
	Armazena_HD text,
	Armazena_SSD text,
	UNIQUE(Sist_Op, Memo_RAM, Placa_video, Processador, 
			Armazena_HD, Armazena_SSD)
);

CREATE TABLE NG_Ofertado(
	Marca text,
	Preço float,
	Descriçao text
)INHERITS(Notebook_gamer);

CREATE TABLE NG_Coletado(
	Preço float,
	Loja_retirada text REFERENCES Loja_cadastrada (Nome),
	Data_coleta date
)INHERITS(Notebook_gamer);

CREATE TABLE Avaliacoes(
	Nota float,
	Descriçao text,
	Nome_avaliador text,
	Data_avaliaçao date,
	UNIQUE(Nome_avaliador, Data_avaliaçao, Descriçao)
);

CREATE TABLE Crawlers(
	Versao text,
	Data_inclusao date,
	Data_Alteracao date,
	Loja_coleta text PRIMARY KEY,
);
