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

Esta etapa é a lógica Java em memória: não inclui telas web, persistência de contas,
expiração de sessões, edição de perfil ou autorização para coleções e objetivos.
Reiniciar o programa apaga os cadastros e sessões. Esses pontos continuam pendentes
para atender a proposta completa do aplicativo.

## Executar (JDK 21, PowerShell)

```powershell
$fontes = @(Get-ChildItem src/main/java,src/test/java -Recurse -Filter *.java | ForEach-Object { $_.FullName })
javac -encoding UTF-8 -d build $fontes
java -ea -cp build br.com.deeplog.AutenticacaoTest
java -cp build br.com.deeplog.Main
```
