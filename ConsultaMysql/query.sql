create database empresa;
use empresa;
create table if not exists funcionarios(
	idfunc int(4) not null primary key auto_increment,
	nomeusuario varchar(35) not null,
	senha varchar(50) not null,
	nome varchar(45) default null,
	funcao varchar(45) default null
) engine = InnoDB;

insert into funcionarios (idfunc,nomeusuario,senha,nome,funcao) values
(1,"Jose","123","Jose","Estagiario"),
(2,"Maria","123","Maria","Programadora");