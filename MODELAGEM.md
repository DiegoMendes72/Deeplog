# modelagem: etapa 3

- `Mergulhador`: nome, e-mail, senha e experiência anterior.
- `Certificacao` e `CertificacaoObtida`: formação e data de conclusão do mergulhador.
- `ObjetivoFormacao` e `ItemListaDesejos`: certificações e destinos planejados.
- `PontoMergulho` e `PerfilMergulho`: local, características e requisitos do mergulho.
- `Requisito`: classe abstrata com o método `verificar`, implementado por `RequisitoCertificacao` e `RequisitoExperiencia`.
- `AvaliadorCompatibilidade`: compara os requisitos do perfil com a formação e a experiência do mergulhador.
- `RegistroMergulho`: pertence a um mergulhador e guarda os dados da experiência.
- `Fotografia` e `AvaliacaoDificuldade`: ficam associadas ao registro. cada registro aceita até cinco fotos e uma capa.
- `Colecao`: agrupa registros do mesmo mergulhador.
- `Carimbo`, `Conquista` e `ConquistaObtida`: representam os locais visitados e as conquistas. as regras usam a interface `RegraConquista`.
- `ServicoGamificacao` e `ServicoEstatisticas`: calculam as conquistas e os indicadores do histórico.
- `ServicoAutenticacao` e `Sessao`: cadastro, login, logout e expiração da sessão.

os atributos são privados e os construtores validam os dados. a compilação, os testes e a execução do main passaram sem erros.
