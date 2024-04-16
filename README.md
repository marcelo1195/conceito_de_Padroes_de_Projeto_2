# Sistema de Registro de Logs

Este sistema é uma API REST desenvolvida com Spring Boot para armazenar mensagens de logs em um banco de dados e permitir a consulta dessas mensagens. O sistema não interage diretamente com o usuário final, dispondo apenas de endpoints para serem consumidos por outros serviços ou para testes.

## Funcionalidades

- **Inserção de mensagens de log**: Endpoint para receber e armazenar mensagens de log.
- **Listagem de todas as mensagens de log com paginação**: Endpoint para listar todas as mensagens de log, suportando paginação para melhor performance em grandes volumes de dados.
- **Filtração de mensagens de log por nível (INFO, WARN, ERRO)**: Endpoint que permite a filtragem das mensagens de log de acordo com o nível de severidade.

## Padrões de Projeto Utilizados

- **DTO (Data Transfer Object)**: Utilizado para transferir as mensagens de log entre a camada de serviço e o controlador.
- **Repository**: Para abstrair e simplificar as operações de banco de dados com o uso do Spring Data JPA.
- **Singleton**: Aplicado para garantir uma única instância do serviço de log, se necessário.

## Tecnologias

- **Spring Boot**: Framework utilizado para criar a aplicação e expor os endpoints da API.
- **Spring Data JPA**: Para interação com o banco de dados, facilitando as operações CRUD.
- **H2 Database**: Banco de dados em memória utilizado para desenvolvimento e testes simplificados.

## Endpoints da API

- **POST /api/logs**: Endpoint para inserir uma nova mensagem de log.
- **GET /api/logs**: Endpoint para listar todas as mensagens de log, com suporte a paginação.
- **GET /api/logs/{level}**: Endpoint para listar mensagens de log filtradas por nível.

## Estrutura do Projeto

### Model
Classe `LogMessage` com atributos como `id`, `message`, `level` e `timestamp`.

### Repository
Interface `LogRepository` que estende `JpaRepository`, facilitando as operações de banco de dados.

### Service
Classe `LogService` que contém a lógica para armazenar e recuperar as mensagens de log.

### Controller
Classe `LogController` que mapeia os endpoints e lida com as requisições HTTP.

## Como Executar

Instruções para executar o projeto localmente:

1. Clone o repositório.
2. Abra o terminal na pasta do projeto.
3. Execute o comando `./mvnw spring-boot:run` para iniciar a aplicação.
4. Acesse os endpoints através de um cliente HTTP como Postman ou através de seu navegador, se aplicável.

## Testes

Descrição de como executar os testes unitários e de integração para garantir que tudo esteja funcionando como esperado.

---

Desenvolvido com :heart: pela equipe [Seu Nome ou Nome da Equipe]
