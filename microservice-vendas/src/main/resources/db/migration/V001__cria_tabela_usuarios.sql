create table usuarios
(
    id bigint not null auto_increment,
    email varchar(255) unique not null,
    password varchar(100) not null,
    nome varchar(255) not null,
    cpf varchar(14) unique not null,
    role varchar(20) not null,
    endereco_cep varchar(10) not null,
    endereco_logradouro varchar(255) not null,
    endereco_cidade varchar(100) not null,

    primary key (id)
);