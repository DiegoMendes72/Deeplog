# Deeplog
Sistema de Registro e Diagnóstico de Mergulho

## Autenticação

`ServicoAutenticacao` oferece cadastro com e-mail único, login e logout.
As senhas são protegidas com PBKDF2-HMAC-SHA256 (600.000 iterações e salt aleatório).
`Mergulhador.verificarSenha` compara a senha informada sem expor o hash.
Cada login gera uma sessão independente; logout invalida essa sessão.
`ServicoRegistros` exige sessão válida para cadastrar, listar e consultar registros,
e impede consultar registros de outro usuário. A interface futura deve usar esses
serviços, mantendo os objetos de domínio dentro do servidor.

O `Main` demonstra cadastro, login, registro de mergulho e logout. A senha nele é
apenas um exemplo de teste, não uma credencial de produção.

Esta etapa é a lógica Java em memória: não inclui telas web, persistência de contas
ou autorização nos serviços para coleções e objetivos. Sessões expiram após oito
horas e o serviço permite atualizar o nome e a experiência anterior do perfil.
Reiniciar o programa apaga os cadastros e sessões. Esses pontos continuam pendentes
para atender a proposta completa do aplicativo.

Veja [MODELAGEM.md](MODELAGEM.md) para o mapeamento das classes da Etapa 2 e os
limites da implementação parcial da Etapa 3.

## Executar (JDK 21, PowerShell)

```powershell
$fontes = @(Get-ChildItem src/main/java,src/test/java -Recurse -Filter *.java | ForEach-Object { $_.FullName })
javac -encoding UTF-8 -d build $fontes
java -ea -cp build br.com.deeplog.AutenticacaoTest
java -ea -cp build br.com.deeplog.ModelagemTest
java -cp build br.com.deeplog.Main
```
