# 🗂️ Sistema de Eventos — Projeto A2 de Tópicos Especiais

Este projeto faz parte da **A2 da disciplina de Tópicos Especiais**. Trata-se de um **sistema de gerenciamento de eventos**, onde os usuários podem:

- 🔸 **Cadastrar eventos.**
- 🔸 **Se inscrever em eventos.**
- 🔸 O próprio usuário realiza o cadastro e gerencia seus eventos e inscrições.

---

## 🚀 Tecnologias utilizadas

| Tecnologia | Versão |
|-------------|--------|
| Micronaut   | 4.7.6  |
| Java        | 21     |
| MySQL       | 8      |
| Docker      | ✅     |

> ⚠️ Obs.: A versão 4.8 do Micronaut apresentou instabilidades com o Java 21, por isso foi utilizada a versão estável 4.7.6.

---

## 🔐 Autenticação

- A autenticação é do tipo **HTTP Basic Auth**.

---

## 🐳 Como executar o projeto com Docker
### 🚀 Subindo a aplicação e o banco de dados:

Execute o comando:

```bash
docker-compose up --build
```

Isso irá subir:

- 🔸 **MySQL** na porta `3307` (externa) e `3306` (interna no container).
- 🔸 **API Micronaut** na porta `8080`.

---

### 🔸 Credenciais do Banco de Dados:

- **Database:** `eventos_db`
- **Usuário:** `usereventos`
- **Senha:** `passeventos`
- **Host (dentro do Docker):** `mysqldb`
- **Host (acesso externo):** `localhost:3307`

---

## 📑 Endpoints da API

| Método | Endpoint                        | Descrição                                  | Corpo da Requisição (JSON)                                      |
| ------ | ------------------------------- | ------------------------------------------ | --------------------------------------------------------------- |
| POST   | `/usuarios`                     | Cadastrar usuário                          | `{ "nome": "string", "email": "string", "senha": "string" }`    |
| GET    | `/usuarios/eventos`             | Listar todos os eventos do usuário logado  | —                                                               |
| GET    | `/usuarios/inscricoes`          | Buscar todos as inscrições do usuário log. | —                                                               |
| POST   | `/eventos`                      | Cadastrar evento                           | `{ "nome": "string", "data": "yyyy-mm-dd", "local": "string" }` |
| GET    | `/eventos`                      | Listar todos os eventos                    | —                                                               |
| GET    | `/eventos/{id}`                 | Buscar evento por ID                       | —                                                               |
| PUT    | `/eventos/{id}`                 | Atualizar evento                           | `{ "nome": "string", "data": "yyyy-mm-dd", "local": "string" }` |
| PUT    | `/eventos/{id}/cancelar`        | Cancelar evento                            | —                                                               |
| PUT    | `/eventos/{id}/concluir`        | Concluir evento                            | —                                                               |
| POST   | `/inscricoes`                   | Realizar inscrição em um evento            | `{ "eventoId": number }`                                        |
| GET    | `/inscricoes`                   | Listar todas as inscrições                 | —                                                               |
| GET    | `/inscricoes/{id}`              | Buscar inscrição por ID                    | —                                                               |
| PUT    | `/inscricoes/cancelar/{id}`     | Cancelar inscrição                         | —                                                               |
| GET    | `/inscricoes/evento/{idEvento}` | Listar inscrições ativas de um evento      | —                                                               |

---

## 🧠 Fluxo de uso do sistema

1. O usuário realiza seu **cadastro** (`POST /usuarios`).
2. Após estar autenticado, o usuário pode:
   - **Criar eventos** que ele irá gerenciar.
   - **Visualizar os eventos que ele criou** (`GET /eventos/usuario`).
   - **Se inscrever em eventos** criados por ele ou outros usuários (`POST /inscricoes`).
3. O usuário pode também:
   - **Atualizar**, **cancelar** ou **concluir eventos** que ele mesmo criou.
   - **Listar suas inscrições** em eventos (`GET /inscricoes/usuario`).
   - **Visualizar quem está inscrito nos seus eventos** (`GET /inscricoes/evento/{idEvento}`).

---

## ✅ Observações

- 🔸 Autenticação obrigatória em todos os endpoints, exceto o cadastro de usuário (`POST /usuarios`).
- 🔸 O sistema controla quem é dono do evento — apenas o criador pode editar, cancelar ou concluir.
- 🔸 As inscrições são livres — qualquer usuário pode se inscrever em qualquer evento ativo. A aplicação também controla quem é o dono da inscrição — apenas o criador pode cancelar a inscrição.

---




