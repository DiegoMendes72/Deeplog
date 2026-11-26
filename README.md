# DeepLog

Passaporte digital de aventuras subaquáticas. O projeto organiza registros de mergulho, fotografias, certificações e destinos de interesse.

## etapa 3 — implementação parcial

Implementação em Java das classes do diagrama da etapa 2, com encapsulamento, construtores, herança e polimorfismo.

- Cadastro, login e logout, com verificação de senha e sessão com expiração;
- Pontos e perfis de mergulho, certificações e verificação de requisitos;
- Registros de mergulho, avaliação de dificuldade, fotografias e coleções;
- Objetivos de formação e lista de desejos;
- Carimbos, conquistas e estatísticas do histórico.

Os dados ficam em memória. Esta entrega não inclui interface web nem banco de dados.

## etapa 4 — persistência, exceções e testes

Evolução da aplicação incorporando robustez, tratamento de falhas e automação:

- **Tratamento de Exceções Personalizadas:** Classes específicas (`ValidacaoException`, `AutenticacaoException`, `PersistenciaException`) com mensagens úteis.
- **Uso Adequado de Coleções:** Aplicação correta de `List`, `Set` e `Map` (`EnumMap`) nas estruturas de dados e regras de negócio.
- **Persistência em Arquivo (I/O):** Leitura e escrita do histórico de mergulhos utilizando os recursos de `java.nio.file` (`RepositorioArquivo`).
- **Testes Unitários:** Validação automatizada utilizando **JUnit 5** (`AutenticacaoTest` e `ModelagemTest`).
- **Código Limpo:** Estrutura organizada por domínio e livre de duplicações.

## etapa 5 — padrões de projeto (GoF), javadoc e refatoração

Evolução arquitetural com a aplicação de padrões de projeto para maior flexibilidade e organização:

- **Strategy (Comportamental):** Aplicado no pacote `gamificacao` para definir de forma desacoplada as diferentes regras de liberação de conquistas e carimbos dos mergulhadores.
- **Template Method (Comportamental):** Aplicado no pacote `requisitos` através da classe abstrata `Requisito`, padronizando o esqueleto do algoritmo de validação (`verificar`) e delegando os critérios específicos de validação às subclasses (`RequisitoExperiencia` e `RequisitoCertificacao`).
- **Javadoc:** Documentação formal adicionada em todas as classes e métodos públicos do sistema (`/** ... */`).

## organização

- `model`: classes do domínio;
- `requisitos`: verificações e resultado de compatibilidade;
- `autenticacao`: cadastro, senha e sessão;
- `gamificacao`: carimbos e regras de conquista;
- `servicos`: estatísticas;
- `persistencia`: leitura e escrita em arquivo (`RepositorioArquivo`);
- `excecoes`: exceções personalizadas do sistema;
- `enums`: tipos e estados;
- `src/test/java`: testes unitários com JUnit 5 (`AutenticacaoTest`, `ModelagemTest`).

A relação entre o diagrama e o código está em [MODELAGEM.md](MODELAGEM.md).

## como executar

O projeto utiliza o **Maven** e requer o JDK 21 instalado. Na pasta raiz do projeto, utilize os comandos abaixo:

**Compilar o projeto:**
```bash
mvn clean compile
```

**Depois de compilar, copie e cole isso para executar:**
```bash
mvn exec:java -Dexec.mainClass="br.com.deeplog.Main"
```

O `Main` permite a entrada interativa de dados pelo usuário (cadastro, configuração de ponto e registro de mergulho), demonstrando compatibilidade, coleções, conquistas, estatísticas, persistência em arquivo (`registros_mergulho.txt`) e logout.

## testes

Para executar a suíte completa de testes unitários automatizados com JUnit 5:

```bash
mvn test
```
