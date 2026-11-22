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

- **Tratamento de Exceções Personalizadas:** Classes específicas (`ValidacaoException`, `AutenticacaoException`, `PersistenciaException`) com mensagens úteis[span_0](start_span)[span_0](end_span).
- **Uso Adequado de Coleções:** Aplicação correta de `List`, `Set` e `Map` (`EnumMap`) nas estruturas de dados e regras de negócio[span_1](start_span)[span_1](end_span).
- **Persistência em Arquivo (I/O):** Leitura e escrita do histórico de mergulhos utilizando os recursos de `java.nio.file` (`RepositorioArquivo`)[span_2](start_span)[span_2](end_span).
- **Testes Unitários:** Validação automatizada utilizando **JUnit 5** (`AutenticacaoTest` e `ModelagemTest`)[span_3](start_span)[span_3](end_span).
- **Código Limpo:** Estrutura organizada por domínio e livre de duplicações[span_4](start_span)[span_4](end_span).

## organização

- `model`: classes do domínio;
- `requisitos`: verificações e resultado de compatibilidade;
- `autenticacao`: cadastro, senha e sessão;
- `gamificacao`: carimbos e regras de conquista;
- `servicos`: estatísticas;
- `persistencia`: leitura e escrita em arquivo (`RepositorioArquivo`)[span_5](start_span)[span_5](end_span);
- `excecoes`: exceções personalizadas do sistema[span_6](start_span)[span_6](end_span);
- `enums`: tipos e estados;
- `src/test/java`: testes unitários com JUnit 5 (`AutenticacaoTest`, `ModelagemTest`)[span_7](start_span)[span_7](end_span).

A relação entre o diagrama e o código está em [MODELAGEM.md](MODELAGEM.md).

## como executar

O projeto utiliza o **Maven** para gerenciar a compilação, dependências e execução. Certifique-se de ter o JDK 21 e o Maven instalados.

Na pasta raiz do projeto, utilize os comandos abaixo:

**Compilar o projeto:**
```bash
mvn exec:java -Dexec.mainClass="br.com.deeplog.Main"
