create database DatabasePI
use DatabasePI
 
create table Aluno (
id_aluno int primary key,
nome_aluno varchar(70) not null,
dataNascimento_aluno datetime not null,
telefone_aluno varchar(15),
cep_aluno varchar(10) not null,
cidade_aluno varchar(50) not null,
rua_aluno varchar(50) not null,
bairro_aluno varchar(50) not null,
registradoPor_funcionario int not null references Funcionario
)
 
create table Funcionario (
id_funcionario int primary key,
nome_funcionario varchar(70) not null,
funcao_funcionario varchar(30) NOT NULL,
telefone_funcionario varchar(15),
cep_funcionario varchar(10) not null,
cidade_funcionario varchar(50) not null,
rua_funcionario varchar(50) not null,
bairro_funcionario varchar(50) not null,
usuario_funcionario varchar(20) unique,
senha_funcionario varchar(20),
nivelDeAcesso_funcionario int
)
 
create table Aula (
id_aula int primary key,
data_aula datetime not null,
horaComeco_aula varchar(5) not null,
horaFim_aula varchar(5) not null,
qtdeVagasDisponiveis_aula int not null,
vagasOcupadas_aula int not null,
sala_aula int not null,
id_aluno int not null references Aluno,
id_funcionario int not null references Funcionario
)
 
insert into Funcionario values(1, 'Thiago', 'Funcao', 'Telefone', 'cep', 'cidade', 'rua', 'bairro', 'usuario', 'senha', 1)
 
insert into Aluno values(1, 'Thiago', '19/09/2004', '(XX)X XXXX-XXXX', 'cep', 'Indaiatuba', 'rua', 'bairro', 1)
 
insert into Aula values(6, '30/05/2024', '08:00', '09:00', 4, 1, 1, 1, 1)
 
drop table Funcionario
 
drop table Aluno
 
drop table Aula
 
update Funcionario set nivelDeAcesso_funcionario = 1 where id_funcionario = 1
 
select * from Funcionario
 
select * from Aluno
 
select * from Aula
 
select * from Funcionario where id_funcionario = 1
 
delete from Funcionario
 
delete from Aluno where id_aluno = 1
 
delete from Aula where id_aula = 1
delete from Aula where id_aula = 2
delete from Aula where id_aula = 3
delete from Aula where id_aula = 4
delete from Aula where id_aula = 5
delete from Aula where id_aula = 6
delete from Aula where id_aula = 7