# Relatórios API

API REST desenvolvida com Java 21 e Spring Boot para gerenciamento de clientes, produtos e vendas, com geração de relatórios em PDF e Excel.

## Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Flyway
- MapStruct
- JasperReports
- Apache POI
- OpenAPI / Swagger
- Docker

## Arquitetura

O projeto utiliza uma arquitetura inspirada em Clean Architecture e Hexagonal Architecture:

```text
com.filipe.relatorios
├── domain
│   ├── model
│   ├── repository
│   └── exception
├── application
│   ├── dto
│   ├── mapper
│   └── service
└── infrastructure
    ├── controller
    ├── persistence
    ├── reports
    └── config
```

## Executando

Suba o PostgreSQL:

```bash
docker compose up -d
```

Execute a aplicação:

```bash
./mvnw spring-boot:run
```

Swagger:

```text
http://localhost:8080/swagger-ui.html
```

## Endpoints

### Clientes

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/clientes` | Cadastra um cliente |
| `GET` | `/clientes` | Lista todos os clientes |
| `GET` | `/clientes/{id}` | Busca um cliente por ID |
| `PUT` | `/clientes/{id}` | Atualiza um cliente |
| `DELETE` | `/clientes/{id}` | Remove um cliente |

### Produtos

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/produtos` | Cadastra um produto |
| `GET` | `/produtos` | Lista todos os produtos |
| `GET` | `/produtos/{id}` | Busca um produto por ID |
| `PUT` | `/produtos/{id}` | Atualiza um produto |
| `DELETE` | `/produtos/{id}` | Remove um produto |

### Vendas

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/vendas` | Cadastra uma venda |
| `GET` | `/vendas` | Lista todas as vendas |
| `GET` | `/vendas/{id}` | Busca uma venda por ID |
| `PUT` | `/vendas/{id}` | Atualiza uma venda |
| `DELETE` | `/vendas/{id}` | Remove uma venda |

### Relatórios

Os relatórios podem ser filtrados por período através dos parâmetros `inicio` e `fim`.

#### Relatório de vendas em PDF

```http
GET /relatorios/vendas/pdf?inicio=2026-01-01T00:00:00&fim=2026-12-31T23:59:59
```

Retorna o relatório de vendas em formato PDF.

#### Relatório de vendas em Excel

```http
GET /relatorios/vendas/excel?inicio=2026-01-01T00:00:00&fim=2026-12-31T23:59:59
```

Retorna o relatório de vendas em formato XLSX.

#### Resumo das vendas

```http
GET /relatorios/vendas/resumo?inicio=2026-01-01T00:00:00&fim=2026-12-31T23:59:59
```

Retorna o resumo das vendas do período, incluindo informações como quantidade de vendas e faturamento total.

## Exemplo de criação de cliente

```http
POST /clientes
Content-Type: application/json

{
  "nmCliente": "João da Silva",
  "email": "joao@email.com"
}
```

## Exemplo de criação de produto

```http
POST /produtos
Content-Type: application/json

{
  "nmProduto": "Notebook",
  "vlPreco": 3500.00
}
```

## Exemplo de criação de venda

```http
POST /vendas
Content-Type: application/json

{
  "cdCliente": 1,
  "cdProduto": 1,
  "qtQuantidade": 2
}
```
