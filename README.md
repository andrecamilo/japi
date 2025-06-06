# API de Usuários com Arquitetura Hexagonal

Esta é uma API REST simples para gerenciamento de usuários, implementada usando Java Spring Boot e seguindo os princípios da Arquitetura Hexagonal (também conhecida como Ports and Adapters).

## Requisitos

- Java 17 ou superior
- Maven 3.6.3 ou superior

## Comandos aplicação

mvn spring-boot:run
mvn clean package

java -jar target/java.api-0.0.1-SNAPSHOT.jar

## Estrutura do Projeto

O projeto está organizado seguindo a Arquitetura Hexagonal:

```
src/main/java/com/japi/userapi/
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

## Docker

### Requisitos
- Docker
- Docker Compose

### Comandos Docker

#### Construir e iniciar a aplicação
```bash
docker-compose up --build
```

#### Executar em background
```bash
docker-compose up -d
```

#### Parar a aplicação
```bash
docker-compose down
```

#### Ver logs
```bash
docker-compose logs -f
```

#### Reconstruir e reiniciar
```bash
docker-compose up --build --force-recreate
```

#### Verificar status
```bash
docker-compose ps
```

#### Parar e remover containers e imagens
```bash
docker-compose down --rmi all
```

### Acessando a aplicação
- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui/index.html

### Estrutura dos arquivos Docker

#### Dockerfile
- Usa multi-stage build para otimizar o tamanho da imagem
- Primeiro estágio: compilação com Maven
- Segundo estágio: execução com JRE Alpine

#### docker-compose.yml
- Define o serviço da aplicação
- Mapeia a porta 8080
- Configura variáveis de ambiente
- Define política de reinicialização
- Cria uma rede dedicada
- Inclui serviço MongoDB:
  - Porta: 27017
  - Volume persistente para dados
  - Banco de dados: userdb

### Acessando os serviços
- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- MongoDB: localhost:27017 