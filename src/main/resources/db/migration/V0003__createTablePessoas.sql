CREATE TABLE pessoas (
    codigo bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null,
    senha varchar(100) not null,
    perfil varchar(50) not null,
    primary key (codigo)
);