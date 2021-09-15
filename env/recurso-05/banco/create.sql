CREATE TABLE integrante(
    id serial PRIMARY KEY,
    nome VARCHAR(50),
    dataDeNascimento DATE,
    CPF VARCHAR(15)
);

CREATE TABLE banda(
    id SERIAL PRIMARY KEY,
    localDeOrigem VARCHAR(100),
    nomeFantasia VARCHAR(100)
);

CREATE TABLE integrante_banda(
    id_banda int,
    id_integrante int,
    FOREIGN KEY (id_banda) REFERENCES banda(id) ON DELETE RESTRICT,
    FOREIGN KEY (id_integrante) REFERENCES integrante(id) ON DELETE RESTRICT,
    PRIMARY KEY(id_banda,id_integrante)
);