# Capítulo 2 - Conectando-se com o BD

Para conectar o spring com um SGBD (Sistema de Gerenciador de Bando de Dados) usamos o JDBC, que foi instalado
junto com o driver do _Postgres_.

Abra o arquivo __application.properties__, que fica na pasta _resources_ e adicione as seguintes linhas:

> spring.datasource.driver-class-name=org.postgresql.Driver

Para definir qual driver o spring deve usar para se conectar a um banco.

> spring.datasource.url=jdbc:postgresql://localhost:5432/library

Para definir qual banco de dados o spring deve se conectar.
Após a última barra é definido qual bd deve ser usado.

> spring.datasource.username=postgres

Configura com qual usuário o spring deve usar ao se conectar com SGBD.
> spring.datasource.password=123456789

Configura com qual senha ele deve usar para fazer a conexão.

> spring.jpa.hibernate.ddl-auto=validate

Define que ao iniciar o servidor o spring verifica se as entidades definidas são correspondentes
com as tabelas do banco de dados.

> spring.output.ansi.enabled=always

Deixa a saída do console colorida.
