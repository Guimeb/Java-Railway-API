
# Projeto Java API - Bootcamp Decola Tech 2025

Este projeto foi desenvolvido durante o Bootcamp Decola Tech 2025 com o objetivo de aplicar conceitos de desenvolvimento backend utilizando Java, Spring Boot, Railway e PostgreSQL. A API tem como objetivo gerenciar usuários, contas e transações, permitindo que o destino realize analises com os dados gerenciados. O relacionamento entre usuário e conta está configurado de forma que, ao atualizar os dados do usuário, os dados da conta também sejam alterados.

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.4
- Spring Data JPA
- Hibernate ORM 6.6.11.Final
- H2 Database (em memória)
- Maven
- PostgreSQL
- Railway

## Funcionalidades

- **Gerenciamento de Usuários:** Criação, leitura, atualização e exclusão.
- **Gerenciamento de Contas:** Atualizadas junto ao usuário automaticamente (cascade).
- **Analise de Transações:** Operações financeiras vinculadas às contas, para entrega e analise de dados.

## Documentação dos Endpoints

### Users

- `GET /users/{id}`: Busca usuário por ID.
- `POST /users`: Cria novo usuário com conta.
- `PUT /users/{id}`: Atualiza dados do usuário e conta associada.
- `DELETE /users/{id}`: Remove usuário e conta associada.

### Account

- `GET /users/{id}`: Busca conta por ID.
- `POST /users`: Cria nova conta em usuário selecionado.
- `PUT /users/{id}`: Atualiza dados da conta associada.
- `DELETE /users/{id}`: Remove a conta associada.

### Transaction

- `GET /transactions/{id}`: Busca por ID da transação.

## Protótipo no Figma

[Acesse o protótipo no Figma](https://figma.com/seu-link-prototipo)

## Diagrama de Classes

classDiagram
    class User {
      +Long id
      +String name
      +List~Account~ accounts
    }
    
    class Account {
      +Long id
      +String number
      +String agency
      +BigDecimal balance
      +BigDecimal credit_limit
      +List~Transaction~ transactions
      +User user
    }
    
    class Transaction {
      +Long id
      +String name
      +BigDecimal transaction_value
      +LocalDate date
      +String description
      +Account account
    }
    
    User "1" o-- "*" Account : has
    Account "1" o-- "*" Transaction : contains
    Account "*" --> "1" User : belongs to

