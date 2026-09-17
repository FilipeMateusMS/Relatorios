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
- JUnit 5 / Mockito

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

## Relatórios

PDF:

```text
GET /relatorios/vendas/pdf?inicio=2026-01-01T00:00:00&fim=2026-12-31T23:59:59
```

Excel:

```text
GET /relatorios/vendas/excel?inicio=2026-01-01T00:00:00&fim=2026-12-31T23:59:59
```

Resumo:

```text
GET /relatorios/vendas/resumo?inicio=2026-01-01T00:00:00&fim=2026-12-31T23:59:59
```

## CRUD

- `GET /clientes`
- `POST /clientes`
- `GET /clientes/{id}`
- `PUT /clientes/{id}`
- `DELETE /clientes/{id}`
- `GET /produtos`
- `POST /produtos`
- `GET /produtos/{id}`
- `PUT /produtos/{id}`
- `DELETE /produtos/{id}`
- `GET /vendas`
- `POST /vendas`
- `GET /vendas/{id}`
- `PUT /vendas/{id}`
- `DELETE /vendas/{id}`
