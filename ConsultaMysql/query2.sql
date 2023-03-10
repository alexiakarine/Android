create database empresa;
use empresa;
create table if not exists funcionarios(
	idfunc int(4) not null primary key auto_increment,
	nomeusuario varchar(35) not null,
	senha varchar(50) not null,
	nome varchar(45) default null,
	funcao varchar(45) default null,
	rg varchar(9) not null,
	cpf varchar(11) not null
) engine = InnoDB;

insert into funcionarios (idfunc,nomeusuario,senha,nome,funcao, rg, cpf) values
(1,"Jose","123","Jose","Estagiario","407450661","18040130450"),
(2,"Maria","123","Maria","Programadora","49461056","67304905009");