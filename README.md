# 🎫 HelpDesk — Sistema de Gestão de Chamados

![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green) ![Status](https://img.shields.io/badge/Status-Conclu%C3%ADdo-blue)

API REST desenvolvida em Java com Spring Boot e JPA/Hibernate para gestão de chamados de suporte técnico, com relacionamento entre usuários (clientes e atendentes), categorias e comentários, tratamento de erros customizado e persistência em banco de dados relacional.

---

## 🎯 Objetivo

Consolidar na prática o desenvolvimento Web Back-end com Java e Spring Boot, aplicando conceitos de arquitetura em camadas, padrões DTO e Projection, tratamento de exceções customizado, validação de dados com Bean Validation, controle transacional e mapeamento de relacionamentos complexos entre entidades com JPA/Hibernate.

---

## 🛠️ Funcionalidades

### 👥 Gestão de Usuários e Perfis

* Cadastro de usuários com perfis distintos (`CLIENTE`, `ATENDENTE`, `ADMIN`) utilizando Enum.
* Regras de negócio específicas para criação de chamados de acordo com o perfil do usuário logado.

### 🎫 Gestão de Chamados

* Abertura de chamados vinculando cliente, atendente e categoria.
* Controle de status (`ABERTO`, `EM_ANDAMENTO`, `RESOLVIDO`, `FECHADO`) e prioridade (`BAIXA`, `MEDIA`, `ALTA`, `URGENTE`) via Enum.
* Mapeamento de relacionamentos `@ManyToOne`/`@OneToMany` entre Chamado, Usuário e Categoria.
* Sistema de comentários vinculados a cada chamado, para registro de histórico e andamento.

### 🗂️ Categorias

* Organização dos chamados por categoria, com listagem dos chamados vinculados a cada uma.

### ✅ Validação e Tratamento de Erros

* Validação de dados de entrada com `@Valid`/`@Validated` e Bean Validation (`@NotNull`, `@NotEmpty`, `@NotBlank`) diretamente nos DTOs.
* Pacote `handler` dedicado para tratamento global de exceções, com subpacotes organizados para `entity`, `exception` e `handler`, entregando respostas de erro padronizadas e mais amigáveis para o consumidor da API.

### 🔍 Consultas e Projeções

* Pacote `projection` com interfaces dedicadas a projeções de dados, otimizando o retorno das consultas sem expor a entidade completa.
* Suporte a paginação (`Pageable`) nas listagens.

### 🔒 Transações e Segurança

* Uso de `@Transactional` nas operações de criação, atualização e remoção, garantindo consistência nos relacionamentos entre entidades.
* Uso de variáveis de ambiente no `application.yaml` para proteger dados sensíveis de configuração.

---

## 🏗️ Arquitetura do Projeto

```
src/
└── main/
    └── java/
        └── Helpdesk/
            ├── controler/          # Camada REST Controller (Endpoints da API)
            │   ├── CategoriaControler
            │   ├── ChamadoControler
            │   ├── ComentarioControler
            │   └── UsuarioControler
            │
            ├── dto/                 # Data Transfer Objects (entrada/saída da API)
            │   ├── CategoriaDto
            │   ├── ChamadoDto
            │   ├── ComentarioChamadoDto
            │   ├── ComentarioUsuarioDto
            │   └── UsuarioDto
            │
            ├── entity/               # Entidades de domínio e mapeamento ORM (JPA)
            │   ├── CategoriaEntity
            │   ├── ChamadoEntity
            │   ├── ComentarioEntity
            │   └── UsuarioEntity
            │
            ├── enums/                 # Enums de domínio (Perfil, Categoria, Status, Prioridade)
            │   ├── Categoria
            │   ├── Perfil
            │   ├── Prioridade
            │   └── Status
	        │
            ├── handler/                # Tratamento global de exceções da API
            │   ├── entity/
            │   ├── exception/
            │   └── handler/
            │
            ├── projection/              # Interfaces de projeção e paginação
            │   ├── CategoriaProjection
            │   ├── ChamadoProjection
            │   └── UsuarioProjection
            │    
            ├── repository/               # Camada de persistência (Spring Data JPA)
            │   ├── ICategoriaRepository
            │   ├── IChamadoRepository
            │   ├── IComentarioRepository
            │   └── IUsuarioRepository
            │
            └── service/                    # Regras de negócio da aplicação
                ├── CategoriaService
                ├── ChamadoService
                ├── ComentarioService
                └── UsuarioService
```

---

## 🧰 Tecnologias e Conceitos Utilizados

* **Java 17** — Linguagem principal de desenvolvimento.
* **Spring Boot 3.x** — Framework para criação da aplicação Web RESTful.
* **Spring Data JPA & Hibernate** — Mapeamento objeto-relacional (ORM) e gerenciamento de entidades.
* **Bean Validation** (`@Valid`, `@Validated`, `@NotNull`, `@NotEmpty`, `@NotBlank`) — Validação dos dados de entrada.
* **Arquitetura em Camadas & Pattern DTO** — Separação de responsabilidades e proteção do modelo de domínio na exposição dos endpoints.
* **Projection** — Interfaces de projeção para otimizar o retorno de dados nas consultas.
* **Exception Handler customizado** — Respostas de erro padronizadas e amigáveis via `@ControllerAdvice`.
* **Relacionamentos JPA** (`@ManyToOne`, `@OneToMany`, `@JoinColumn`) — Modelagem de chamados vinculados a usuários e categorias.
* **@Transactional** — Controle transacional em operações de criação, atualização e remoção.
* **Variáveis de ambiente** — Configuração segura de dados sensíveis no `application.yaml`.

---

## ▶️ Como Executar

* Java 17+ instalado.
* Maven instalado (ou utilizar o wrapper `./mvnw`).
* Banco de dados relacional configurado (variáveis de ambiente definidas no `application.yaml`).

```bash
git clone https://github.com/YudiOgusuko/helpdesk-treino.git
cd helpdesk-treino
./mvnw spring-boot:run
```

---

## 👤 Sobre o Projeto

Projeto desenvolvido como prática de consolidação de Spring Boot e JPA/Hibernate, com foco em arquitetura em camadas, relacionamentos entre entidades, validação de dados e tratamento de exceções, simulando um sistema real de chamados de suporte técnico (Helpdesk).
