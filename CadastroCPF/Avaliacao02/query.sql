drop database if exists empresa;
create database empresa;
use empresa;

drop table matricula;

create table if not exists matricula(
	id serial,
    matricula int unique not null,
    nome varchar(50) not null,
    departamento varchar(50) not null,
    salario_total decimal(10,2) not null,
    salario_receber decimal(10,2) not null,
    faltas decimal(10,2) not null,
    horas_extras decimal(10,2) not null,
    convenio_medico varchar(50),
    rg varchar(15),
    cpf varchar(16)
);

insert into matricula (matricula,nome,departamento,salario_total,salario_receber,faltas,horas_extras,convenio_medico,rg,cpf) values (1,'$nome','$departamento',2,3,4,5,'$conveniomedico','$rg','$cpf');

select * from matricula;

truncate matricula;