# 🎬 Cinema

## 🛠️ Tecnologias Utilizadas


![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Git](https://img.shields.io/badge/Git-E34F26?style=for-the-badge&logo=git&logoColor=white)
![VSCode](https://img.shields.io/badge/VSCode-0078D4?style=for-the-badge&logo=visual%20studio%20code&logoColor=white)
![Java](https://img.shields.io/badge/Java-CC342D?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white) ![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white) ![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white) ![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white) ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![JUnit5](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![MySQL](    https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

<details>

  <summary>
      Atividade 1
  </summary>

  A empresa recebe uma nova demanda de uma casa cultural da cidade que conta com uma sala de cinema. O objetivo é montar um site para o estabelecimento, porém há uma seção específica na qual você trabalhará. Além da divulgação das ações do estabelecimento, a casa cultural quer um espaço em que as pessoas possam registrar os filmes a que assistiram e avaliar cada um deles (fornecer análises).

  Concentrando-se nessa funcionalidade, neste momento de avaliações e protótipos, você deverá criar um sistema web que permita cadastrar filmes e realizar análises desses filmes cadastrados. Para isso, você precisará de duas entidades Model:

  - **Filme:** id, título, sinopse, gênero e ano de lançamento
  - **Análise:** id, filme, análise e nota

  O cliente está ansioso para ver o projeto e, portanto, para fins de testes iniciais, o desenvolvimento deve ser feito utilizando apenas armazenamento em memória _(sem banco de dados)_. Inclua, no sistema, páginas para cadastrar filmes e listar os filmes já cadastrados e uma página de detalhes que exibirá todas as informações sobre o filme selecionado, além de permitir adicionar uma análise.

  Desenvolva um sistema web Spring MVC sem banco de dados, de acordo com a descrição do contexto. Preste atenção nos passos a seguir:
  
  - Comece criando um projeto no Spring Initializr.

  - Defina as entidades Filme e Análise.

  - Crie classes de controlador para manipular as requisições HTTP (hyper text transfer protocol), como cadastrar um filme assistido e adicionar uma análise.

  - Implemente as visualizações – páginas HTML (hyper text markup language) – para a interação do usuário, como formulários para cadastrar um filme e adicionar uma análise.

  - Teste o sistema web sem banco de dados, verificando se as funcionalidades básicas estão funcionando corretamente.
</details>

<details>
  <summary>
      Atividade 2
  </summary>

  O cliente ficou muito animado com o seu último projeto e viu grande potencial no sistema que você está construindo.
  Ele imagina ser possível expandir, para que outros sites utilizem o sistema de avaliação, ou até mesmo criar aplicativos móveis com essas funcionalidades.
  Uma alternativa muito interessante para essas integrações entre sistemas é a disponibilização de uma API (application programming interface) REST.
  
  Nesta etapa, portanto, sua tarefa será criar uma prova de conceito para essa ideia, desenvolvendo uma API REST com base no projeto implementado na atividade anterior.
  Para essas funcionalidades, haverá uso de banco de dados, e não mais apenas dados em memória.
  
  Crie um novo controlador REST que atenderá às requisições GET, POST, PUT e DELETE.
  É importante que a API REST consiga atender a todas as requisições e que os dados persistam em um banco de dados. Os testes acontecerão via Postman.
  
  Siga os passos a seguir:

  - Crie um novo controlador no projeto iniciado na atividade anterior.
  - Configure o banco de dados (MySQL) para armazenar os dados dos filmes e as análises.
  - Implemente os controladores REST para manipular as requisições GET, POST, PUT e DELETE relacionadas aos filmes e às análises.
  - Realize testes das chamadas à API utilizando o Postman e registre os testes feitos com capturas de tela de cada requisição.
</details>

## 📋 Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:

- [Java 17+](https://adoptium.net/)
- [Maven 3.6+](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/) (para Dev Container e MySQL)
- Um navegador web (Chrome, Firefox, etc.)

## 🔧 Instalação e Execução

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/cinema.git
   cd cinema
   ```

2. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

3. **Acesse no navegador:**
    http://localhost:8080

## 🌐 Páginas Disponíveis

- **/**: Página inicial com boas-vindas.
- **/filmes**: Lista todos os filmes cadastrados.
- **/filmes/novo**: Formulário para cadastrar um novo filme.
- **/filmes/{id}**: Detalhes do filme selecionado, incluindo análises.
- **/filmes/{id}/avaliar**: Formulário para adicionar uma análise ao filme.
- **/analises**: Lista todas as análises
- **Swagger UI:** http://localhost:8080/swagger-ui.html

## 🧪 Testes [BÔNUS]

O projeto possui uma suíte completa de testes

### Executar Testes

```bash
mvn test
```

**Cobertura:** Todos os testes passam, validando funcionalidades básicas e casos de erro.

## Dependências Principais
<img src="https://img.shields.io/badge/Java-17+-red.svg?style=for-the-badge" alt="Java 17+">
<img src="https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg?style=for-the-badge" alt="Spring Boot 3.2.0">
<img src="https://img.shields.io/badge/Maven-3.6+-orange.svg?style=for-the-badge" alt="Maven 3.6+">
<img src="https://img.shields.io/badge/MySQL+-blue.svg?style=for-the-badge" alt="MySQL">
<img src="https://img.shields.io/badge/Status-Desenvolvimento-danger.svg?style=for-the-badge" alt="Status: Desenvolviment0">