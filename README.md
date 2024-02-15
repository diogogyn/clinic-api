# API Rest - Clinica médica
## Versão 1.0

Esta é uma API Rest implementada em Java 17 com o framework Spring Boot, para fazer a gestão de horarios de atendimento de uma clinica. Por se tratar de uma primeira versão, esta ainda conta com recursos limitados.

## Funcionalidades

- Cadastro, atualização e exclusão de medicos;
- Cadastro, atualização e exclusão de pacientes;
- Cadastro de usuários (sem perfis de acesso);
- Agendamento e cancelamento de consultas;
- API Docs para auxilio de desenvolvedores;

## Tech
Para implementar esta aplicação foram utilizados os seguntes recursos:
- [SpringBoot] - Java 17 + Spring Boot
- [Mysql] - JDBC Mysql
- [Flyway] - Flyway p/ migração de banco de dados
- [Auth0] - Lib JWT da Auth0
- [JwtIo] - JSON Web Token (JWT)
- [SpringDoc] - Spring Doc Open Api
- [lombok] - Lombok
- [Maven] - Apache Maven

## Deployment

Padrão
```bash
  java -jar clinic-api-0.0.1-SNAPSHOT.jar 
```
Em prod:

```bash
  java -Dspring.profiles.active=prod -jar clinic-api-0.0.1-SNAPSHOT.jar 
```
## Installation

Dillinger requires [Node.js](https://nodejs.org/) v10+ to run.

Install the dependencies and devDependencies and start the server.

```sh
cd dillinger
npm i
node app
```


[maven: <https://maven.apache.org/>
[lombok]: <https://projectlombok.org/>
[SpringDoc]: <https://springdoc.org/>
[JwtIo]: <https://jwt.io/libraries>
[Auth0]: <https://github.com/auth0>
[Flyway]: <https://flywaydb.org/>
[Mysql]: <https://www.mysql.com/>
[SpringBoot]: <https://spring.io/projects/spring-boot>

[PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
[PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
[PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
[PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
[PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
[PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
