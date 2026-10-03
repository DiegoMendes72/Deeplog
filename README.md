# DeepLog

passaporte digital de aventuras subaquáticas. o projeto organiza registros de mergulho, fotografias, certificações e destinos de interesse.

## etapa 3 — implementação parcial

implementação em Java das classes do diagrama da etapa 2, com encapsulamento, construtores, herança e polimorfismo.

- cadastro, login e logout, com verificação de senha e sessão com expiração;
- pontos e perfis de mergulho, certificações e verificação de requisitos;
- registros de mergulho, avaliação de dificuldade, fotografias e coleções;
- objetivos de formação e lista de desejos;
- carimbos, conquistas e estatísticas do histórico.

os dados ficam em memória. esta entrega não inclui interface web nem banco de dados.

## organização

- `model`: classes do domínio;
- `requisitos`: verificações e resultado de compatibilidade;
- `autenticacao`: cadastro, senha e sessão;
- `gamificacao`: carimbos e regras de conquista;
- `servicos`: estatísticas;
- `enums`: tipos e estados;
- `src/test/java`: testes de autenticação e modelagem.

a relação entre o diagrama e o código está em [MODELAGEM.md](MODELAGEM.md).

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
