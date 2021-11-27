CREATE DATABASE notebook_store_integ;

CREATE TABLE Loja_cadastrada(
	Nome text,
	Website text,
	PRIMARY KEY(nome)
);

CREATE TABLE Loja_fornecedora(
	Nome text,
	Website text,
	PRIMARY KEY(nome)
);

CREATE TABLE Esp_tecnicas(
	Modelo_note text PRIMARY KEY,
	Sist_Op text,
	Memo_RAM text,
	Placa_video text,
	Processador text,
	Armazena_HD text,
	Armazena_SSD text,
	UNIQUE(Sist_Op, Memo_RAM, Placa_video, Processador, 
			Armazena_HD, Armazena_SSD)
);

CREATE TABLE Notebook_gamer(
	Marca text,
	Preço float,
	Descriçao text
)INHERITS(Esp_tecnicas);

CREATE TABLE Hist_notebook_dados(
	Preço float,
	Loja_retirada text REFERENCES Loja_cadastrada (Nome),
	Data_coleta date
)INHERITS(Esp_tecnicas);

CREATE TABLE Avaliacoes(
	Nota float,
	Descriçao text,
	Nome_avaliador text,
	Data_avaliaçao date,
	UNIQUE(Nome_avaliador, Data_avaliaçao, Descriçao)
);

