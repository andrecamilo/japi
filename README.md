# API de Usuários com Arquitetura Hexagonal

Esta é uma API REST simples para gerenciamento de usuários, implementada usando Java Spring Boot e seguindo os princípios da Arquitetura Hexagonal (também conhecida como Ports and Adapters).

## Requisitos

- Java 17 ou superior
- Maven 3.6.3 ou superior

## Estrutura do Projeto

O projeto está organizado seguindo a Arquitetura Hexagonal:

```
src/main/java/com/example/userapi/
├── domain/           # Camada de domínio (core)
│   ├── model/       # Entidades de domínio
│   ├── port/        # Portas (interfaces)
│   └── service/     # Casos de uso
└── infrastructure/  # Camada de infraestrutura
    ├── controller/  # Controladores REST
    └── repository/  # Implementações dos repositórios
```

## Endpoints da API

- `POST /api/users`: Criar um novo usuário
- `GET /api/users`: Listar todos os usuários
- `GET /api/users/{id}`: Buscar usuário por ID
- `DELETE /api/users/{id}`: Deletar usuário por ID

## Como Executar

1. Clone o repositório
2. Execute o comando:
   ```bash
   ./mvnw spring-boot:run
   ```
3. A API estará disponível em `http://localhost:8080`

## Exemplo de Uso

### Criar um usuário
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name": "João Silva", "email": "joao@email.com"}'
```

### Listar todos os usuários
```bash
curl http://localhost:8080/api/users
```

### Buscar usuário por ID
```bash
curl http://localhost:8080/api/users/1
```

### Deletar usuário
```bash
curl -X DELETE http://localhost:8080/api/users/1
``` 