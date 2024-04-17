# Sistema de Gerenciamento de Usuários

Este projeto é uma API REST desenvolvida com Spring Boot para gerenciar usuários e tarefas. O sistema utiliza o Spring Security para garantir a segurança e o Spring Data JPA para interação com o banco de dados.

## Funcionalidades

- Criação, atualização, recuperação e exclusão de usuários (CRUD).
- Codificação de senha com BCrypt para segurança aprimorada.
- Documentação de API automatizada com Swagger/OpenAPI.

## Tecnologias

- **Spring Boot**: Framework utilizado para facilitar o desenvolvimento da aplicação.
- **Spring Security**: Fornece autenticação e autorização.
- **Spring Data JPA**: Facilita as operações CRUD com o banco de dados.
- **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
- **Swagger**: Gera documentação interativa para a API.

## Segurança

Utilizamos o Spring Security para proteger as rotas da API e garantir que as senhas dos usuários sejam armazenadas de forma segura no banco de dados. A codificação de senha é realizada pelo `BCryptPasswordEncoder`.

## Endpoints da API

- `POST /api/users` - Cria um novo usuário.
- `GET /api/users` - Lista todos os usuários.
- `GET /api/users/{id}` - Busca um usuário por ID.
- `PUT /api/users/{id}` - Atualiza os detalhes de um usuário existente.
- `DELETE /api/users/{id}` - Deleta um usuário.

## Configuração e Execução

Instruções para configurar e executar o projeto localmente:

1. Clone o repositório.
2. Navegue até a pasta do projeto e execute `./mvnw spring-boot:run`.
3. Acesse os endpoints através de um navegador ou cliente HTTP como Postman.
4. A documentação da API pode ser acessada em `http://localhost:8080/swagger-ui.html`.

## Testes

Descrição de como executar os testes unitários e de integração:

```shell
./mvnw test
