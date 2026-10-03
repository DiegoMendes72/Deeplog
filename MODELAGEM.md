# Implementação parcial — Etapa 3

As classes do diagrama foram distribuídas entre os pacotes `model`, `requisitos`,
`gamificacao`, `autenticacao` e `servicos`. Os tipos auxiliares estão em `enums`.

| Parte do diagrama | Implementação |
| --- | --- |
| Perfil e formação | Mergulhador, Certificacao, CertificacaoObtida, ObjetivoFormacao |
| Planejamento | ItemListaDesejos, EstadoDesejo, EstadoObjetivo |
| Catálogo | PontoMergulho, PerfilMergulho, FormaEntrada |
| Requisitos | Requisito abstrato, RequisitoCertificacao, RequisitoExperiencia, Verificacao |
| Compatibilidade | AvaliadorCompatibilidade, ResultadoCompatibilidade |
| Diário | RegistroMergulho, Fotografia, AvaliacaoDificuldade |
| Coleções | Colecao com proprietário e conjunto de registros |
| Gamificação | Carimbo, Conquista, ConquistaObtida, RegraConquista, ServicoGamificacao |
| Estatísticas | ServicoEstatisticas, RelatorioEstatistico |
| Autenticação | ServicoAutenticacao, Sessao com expiração e encerramento |

Os atributos são privados; operações validam estados e coleções retornadas não
permitem alterar sua estrutura externamente. Herança e polimorfismo aparecem nos
requisitos. Regras de conquista usam uma interface com implementações diferentes.

Foram preservados alguns nomes anteriores por compatibilidade: `getMergulhador`
também possui `getAutor`, `getDataObtencao` possui `getDataConclusao`, e os nomes
anteriores de profundidade e organização emissora possuem acessores correspondentes
ao diagrama. Duração permanece `double` para preservar frações de minuto. As classes
de pontos, níveis e desafios anteriores permanecem como extensões da implementação.

Esta entrega é parcial e utiliza memória. Persistência e interface web não foram
implementadas. As coleções e regras funcionam no domínio; sua integração completa
com serviços de sessão e armazenamento continua sendo uma etapa posterior.
Os serviços de gamificação e estatísticas recebem o histórico em memória; após
alterá-lo, deve-se chamar `recalcular` para atualizar carimbos e conquistas.

Testes (JDK 21): `AutenticacaoTest` e `ModelagemTest`, ambos executados com `-ea`.
Os testes cobrem isolamento, senha inválida, logout, três resultados de requisitos,
objetivo que não substitui certificação obtida, propriedade de coleções, limite e
capa das fotos, estatísticas vazias, recálculo de conquistas e validade da sessão.
