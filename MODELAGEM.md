# modelagem — etapa 3

as classes do diagrama foram implementadas em Java e separadas por função:

- perfil e formação: mergulhador, certificações e objetivos;
- planejamento: lista de desejos;
- catálogo: pontos e perfis de mergulho;
- requisitos: certificação, experiência e resultado de compatibilidade;
- diário: registros, fotografias, dificuldade e coleções;
- gamificação: carimbos, conquistas e regras;
- serviços: autenticação, sessão e estatísticas.

os atributos são privados e os construtores validam os dados. a herança aparece nos tipos de requisito. o polimorfismo permite verificar cada requisito pelo mesmo método. as regras de conquista usam uma interface.

coleções guardam registros do proprietário. cada registro aceita até cinco fotos, com uma escolhida como capa. os requisitos retornam atendido, pendente ou indeterminado.

alguns nomes anteriores foram mantidos no código. a duração usa `double` para aceitar frações de minuto.

os testes de autenticação e modelagem e a execução do main passaram com JDK 21.
