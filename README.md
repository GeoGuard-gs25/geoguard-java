# ⚡ GeoGuard - Energy Control API

API desenvolvida em **Spring Boot** para gerenciar usuários, contas de energia elétrica e notificações. O acesso é protegido por autenticação JWT.

---

## 🛡️ Autenticação via JWT

### 🔐 1. Registro de novo usuário

```bash
curl -X POST http://localhost:8080/auth/register -H "Content-Type: application/json" -d '{
  "name": "João Silva",
  "email": "joao@email.com",
  "password": "senha123"
}'
```

> 🔁 Esse endpoint também realiza login automático e retorna o JWT no corpo da resposta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsIn..."
}
```

---

### 🔐 2. Login

```bash
curl -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d '{
  "email": "joao@email.com",
  "password": "senha123"
}'
```

> 🔑 **Copie o token JWT** retornado e utilize nas requisições abaixo, no cabeçalho:

```http
Authorization: Bearer SEU_TOKEN_AQUI
```

---

## 📦 Entidades

### 👤 User

Campos: `id`, `name`, `email`, `password`, `bills`

### ⚡ EnergyBill

Campos: `id`, `owner`, `consumoKwh`, `valorKwh`, `amount`, `month`

### 🔔 Notification

Campos: `id`, `title`, `message`, `dataEnvio`, `lida`

---

## 📮 Endpoints protegidos (exigem JWT)

### ⚡ Criar nova conta

```bash
curl -X POST http://localhost:8080/energy-bills -H "Authorization: Bearer SEU_TOKEN_AQUI" -H "Content-Type: application/json" -d '{
  "consumoKwh": 300.5,
  "valorKwh": 0.75,
  "amount": 225.38,
  "month": "2025-05"
}'
```

### 🔔 Criar nova notificação

```bash
curl -X POST http://localhost:8080/notifications -H "Authorization: Bearer SEU_TOKEN_AQUI" -H "Content-Type: application/json" -d '{
  "title": "Nova conta gerada",
  "message": "Sua conta foi emitida com sucesso.",
  "lida": false
}'
```

### ⚡ Listar contas

```bash
curl http://localhost:8080/energy-bills -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

### 🔔 Listar notificações

```bash
curl http://localhost:8080/notifications -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

### ⚡ Atualizar conta

```bash
curl -X PUT http://localhost:8080/energy-bills/1 -H "Authorization: Bearer SEU_TOKEN_AQUI" -H "Content-Type: application/json" -d '{
  "consumoKwh": 350.0,
  "valorKwh": 0.80,
  "amount": 280.00,
  "month": "2025-06"
}'
```

### 🗑️ Deletar conta

```bash
curl -X DELETE http://localhost:8080/energy-bills/1 -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

---

## 🚀 Como rodar o projeto

### ✅ Pré-requisitos

- Java 17+
- Maven 3.8+
- PostgreSQL
- IDE (IntelliJ, Eclipse, VS Code)

---

### ▶️ Executando

```bash
./mvnw spring-boot:run
```

Acesse:

- API: http://localhost:8080

---

## 🧪 Teste com Postman ou Insomnia

1. Crie usuário via `/users` ou faça login em `/login`
2. Salve o token JWT
3. Adicione nos headers das requisições protegidas:

```
Authorization: Bearer SEU_TOKEN
```

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- JWT
- Hibernate Validator
- PostgreSQL
- Lombok

---

## 📂 Repositório do Projeto

Todos os repositórios estão disponiveis em nossa organização do github:  
📎 [Link da organização](https://github.com/GeoGuard-gs25)

## 📹 Vídeos do Projeto

- 🎥 **Apresentação da Solução GeoGuard:**  
  [Link do vídeo](https://youtu.be/YNFErko31fM?si=hFlQK_vrn-SA1UdO)


## 👨‍💻 Autores
-  Guilherme Alves Pedroso - RM555357
-  João Vitor Silva Nascimento - RM554694
-  Rafael Souza Bezerra - 557888
