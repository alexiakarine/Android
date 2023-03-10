drop database loja;
create database if not exists loja;

use loja; 
create table if not exists cliente
(
	id int primary key auto_increment, 
    nome varchar (45),
    endereco varchar (45), 
    telefone varchar (45),
    rg varchar (9), 
    cpf varchar (11),
    nascimento varchar(10)
); 


insert into cliente values(2,"maria","rua banana","00 0000 0000","456879878","42475678942","2010-10-01");