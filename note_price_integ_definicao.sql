CREATE TABLE Loja_cadastrada(
    Nome text,
    Website text,
    PRIMARY KEY(nome)
);

CREATE TABLE Crawlers(
    Id SERIAL PRIMARY KEY UNIQUE,
    Versao text,
    Data_Coleta varchar,
    Data_Alteracao varchar,
    Loja_coleta text
);

CREATE TABLE Notebook_gamer(
    Nome text,
    Modelo text,
    Sist_Op text,
    Memo_RAM text,
    Placa_video text,
    Processador text,
    Armazena_HD text,
    Armazena_SSD text,
    Marca text,
    PRIMARY KEY(Modelo)
);

CREATE TABLE NG_Coletado(
    Id SERIAL PRIMARY KEY,
    Modelo_coletado text REFERENCES Notebook_gamer(Modelo),
    Preco double precision,
    Loja_retirada text REFERENCES Loja_cadastrada (Nome),
    Data_coleta text
);

CREATE TABLE Avaliacoes(
    Modelo_avaliacao text REFERENCES Notebook_gamer(Modelo),
    Nota double precision,
    Descricao text,
    Nome_avaliador text
)