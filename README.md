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

requer JDK 21. na pasta do projeto, compile e execute:

**Windows — PowerShell**

```powershell
$fontes = @(Get-ChildItem src/main/java,src/test/java -Recurse -Filter *.java | ForEach-Object { $_.FullName })
javac -encoding UTF-8 -d build $fontes
java -cp build br.com.deeplog.Main
```

**Linux ou GitHub Codespaces**

```bash
find src/main/java src/test/java -name '*.java' > fontes.txt
javac -encoding UTF-8 -d build @fontes.txt
java -cp build br.com.deeplog.Main
```

o main demonstra cadastro, login, registro, coleção, compatibilidade, conquistas, estatísticas e logout.

## testes

após compilar, execute:

```text
java -ea -cp build br.com.deeplog.AutenticacaoTest
java -ea -cp build br.com.deeplog.ModelagemTest
```
