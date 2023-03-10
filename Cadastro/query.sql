create database if not exists cadastro;
use cadastro;
create table if not exists usuario
(
	nome varchar (45),
    cpf varchar (15),
    rg varchar (15),
    idade varchar (3),
    email varchar (50), 
    telefone varchar (15)
); 

insert into usuario values ('Heloisa', '453.199.858-86', '39.739.844-x', '21','helo@hotmail.com','1298815-0801');
select * from usuario;