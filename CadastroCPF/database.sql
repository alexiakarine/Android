create database if not exists usuarios;
use usuarios;

create table if not exists contas(
	id int primary key auto_increment,
	cpf varchar(11) unique,
	nome varchar(45) not null,
	rg varchar(9) not null,
	telefone varchar(20),
	nascimento varchar(10)
);

select * from contas;