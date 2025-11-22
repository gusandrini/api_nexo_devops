# NEXO API — O Futuro do Trabalho

## Integrantes
- **Eduarda Tiemi Akamini Machado** — RM 554756
- **Felipe Pizzinato Bigatto** — RM 555141
- **Gustavo de Oliveira Turci Sandrini** — RM 557505

---

## Visão Geral
A NEXO API é uma solução inovadora para análise de perfil profissional e predição de mudança de carreira, colaborando diretamente com o tema "O Futuro do Trabalho". Utiliza Inteligência Artificial, mensageria assíncrona, internacionalização e recursos modernos de segurança, performance e escalabilidade.

---

## Tecnologias Utilizadas
- **Java 17 / Spring Boot 3.2.8**
- **Spring Data JPA** (persistência)
- **Spring Security** (JWT para API, sessão/CSRF para web)
- **Bean Validation**
- **Spring Cache**
- **RabbitMQ** (mensageria)
- **Thymeleaf** (templates web)
- **Internacionalização (i18n)** — pt/en
- **Spring AI** (IA generativa — opcional)
- **Deploy em nuvem** (Azure, AWS, GCP — instruções abaixo)

---

## Como a Solução Colabora com o Tema
A API NEXO permite que profissionais avaliem sua trajetória, recebam predições inteligentes sobre mudança de carreira e acompanhem seu histórico. Isso empodera o usuário para decisões mais informadas, alinhadas às tendências do futuro do trabalho, como automação, reskilling e personalização de carreira.

---

## Como Rodar o Projeto
1. **Pré-requisitos:**
   - Java 17+
   - Maven
   - RabbitMQ (local ou cloud)
   - Banco de dados Oracle (ou H2 para testes)
2. **Clone o repositório:**
   ```bash
   git clone https://github.com/dudatiemiak/api_nexo.git
   cd api_nexo
   ```
3. **Configure o banco e RabbitMQ em `application.properties`**
4. **Build e execute:**
   ```bash
   ./mvnw clean package
   ./mvnw spring-boot:run
   ```
5. **Acesse:**
   - Web: `http://localhost:8080`
   - Swagger: `http://localhost:8080/swagger-ui.html`

---

## Exemplos de Requisições
### API REST — Predição de Mudança de Carreira
```http
POST /api/predicoes/predizer
Content-Type: application/json
Authorization: Bearer <token>

{
  "nrIdade": 30,
  "nrSalario": 5000,
  "idOcupacao": 1,
  "idCampoEstudo": 2,
  "idNivelEducacional": 3,
  "idInfluenciaFamiliar": 1,
  "qtdaAnosExperiencia": 8,
  "dsSatisfacao": 7,
  "dsTecnologia": 8,
  "dsMudanca": 5
}
```
**Resposta:**
```json
{
  "probabilidade_mudar": 0.42,
  "classe_prevista": 1
}
```

---

## Rotas e Funcionalidades
### Web Interface
- `/` — Home
- `/descricao-clientes/novo` — Formulário para nova descrição profissional, instruções, predição IA
- `/descricao-clientes/historico` — Histórico de descrições e predições
- `/login` — Autenticação
- `/usuario/novo` — Cadastro de usuário

### API REST
- `POST /api/predicoes/predizer` — Predição IA para mobile/web
- `GET /api/usuarios` — Listagem paginada de usuários (com cache)
- `GET /api/usuarios/{id}` — Buscar usuário por ID
- `GET /api/usuarios/email/{email}` — Buscar usuário por e-mail
- `POST /api/usuarios` — Criar usuário
- `PUT /api/usuarios/{id}` — Atualizar usuário
- `DELETE /api/usuarios/{id}` — Remover usuário

### Mensageria
- Envio de eventos para RabbitMQ ao criar/atualizar usuários

### Internacionalização
- Suporte completo a português e inglês (interface e mensagens)

### Segurança
- JWT para API
- Sessão/CSRF para web
- Controle de acesso por perfil

---

## Deploy em Nuvem
- Pode ser realizado em Azure, AWS ou GCP
- Recomenda-se uso de Docker para facilitar o deploy
- Exemplo Docker:
  ```dockerfile
  FROM openjdk:17-jdk
  COPY target/api_nexo-0.0.1-SNAPSHOT.jar app.jar
  ENTRYPOINT ["java", "-jar", "app.jar"]
  ```

---

## Licença
Projeto acadêmico — todos os direitos reservados aos autores.
