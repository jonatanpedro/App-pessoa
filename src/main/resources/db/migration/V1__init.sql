CREATE TABLE pessoa (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cpf varchar(20) NOT NULL,
    nome varchar(100) NOT NULL,
    sexo char(1),
    email varchar(100),
    dt_nascimento date NOT NULL,
    naturalidade varchar(100),
    nacionalidade varchar(100),
    endereco varchar(255),
    creation_time timestamp,
    modification_time timestamp,
    PRIMARY KEY (id)
);