create database DatabasePI 
use DatabasePI 

create table Aluno ( 
id_aluno int primary key, 
nome_aluno varchar(70) not null, 
dataNascimento_aluno varchar(10) not null, 
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
telefone_funcionario varchar(15), 
cep_funcionario varchar(10) not null, 
cidade_funcionario varchar(50) not null, 
rua_funcionario varchar(50) not null, 
bairro_funcionario varchar(50) not null, 
usuario_funcionario varchar(20), 
senha_funcionario varchar(20), 
nivelDeAcesso_funcionario int not null 
)

insert into Funcionario values(1, 'Thiago', 'Telefone', 'cep', 'cidade', 'rua', 'bairro', 'usuario', 'senha', 1)   

drop table Funcionario 

drop table Aluno 

drop table Aula 

drop table pagamento 

select * from Funcionario

select * from Aluno

delete from Funcionario where id_funcionario = 1

create table Pagamento ( 
id_pagamento int primary key, 
data_pagamento varchar(10) not null, 
horario_pagamento varchar(10) not null, 
valorMensalidade_pagamento float not null, 
id_aluno int not null references Aluno, 
id_funcionario int not null references Funcionario 
)

create table Aula ( 
id_aula int primary key, 
data_aula varchar(10) not null, 
horaComeco_aula varchar(10) not null, 
horaFim_aula varchar(10) not null, 
qtdeVagasDisponiveis_aula int not null, 
vagasOcupadas_aula int not null, 
sala_aula int not null 
)