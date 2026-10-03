package br.com.deeplog;
import br.com.deeplog.model.*;
import br.com.deeplog.enums.*;
import br.com.deeplog.requisitos.*;
import br.com.deeplog.gamificacao.*;
import br.com.deeplog.autenticacao.*;
import br.com.deeplog.servicos.*;
import java.time.*;
import java.util.*;

public class ModelagemTest {
    public static void main(String[] args) {
        Mergulhador ana = new Mergulhador("Ana", "ana@example.com", "hashExemplo");
        Mergulhador bia = new Mergulhador("Bia", "bia@example.com", "hashExemplo");
        PontoMergulho ponto = new PontoMergulho("Ponto", "Local", "Descrição", "Calmo");
        PerfilMergulho perfil = new PerfilMergulho(ponto, "Perfil", 20);
        perfil.adicionarRequisito(new RequisitoExperiencia("10 mergulhos", "Manual", 10));
        AvaliadorCompatibilidade avaliador = new AvaliadorCompatibilidade();
        assert avaliador.avaliar(ana, perfil).estado() == EstadoCompatibilidade.INDETERMINADO;
        ana.atualizarPerfil("Ana", 0);
        assert avaliador.avaliar(ana, perfil).estado() == EstadoCompatibilidade.PENDENTE;
        ana.atualizarPerfil("Ana", 10);
        assert avaliador.avaliar(ana, perfil).estado() == EstadoCompatibilidade.ATENDIDO;
        Certificacao cert = new Certificacao("Open Water", "PADI", "Inicial");
        ObjetivoFormacao objetivo = new ObjetivoFormacao(ana, cert);
        objetivo.atualizarEstado(EstadoObjetivo.CONCLUIDO);
        Requisito req = new RequisitoCertificacao("Certificação", "PADI", Set.of(cert));
        assert req.verificar(ana).estado() == EstadoVerificacao.PENDENTE;
        ana.adicionarCertificacao(new CertificacaoObtida(cert, LocalDate.now(), "123"));
        assert req.verificar(ana).estado() == EstadoVerificacao.ATENDIDO;
        ItemListaDesejos desejo = new ItemListaDesejos(ana,ponto,perfil);
        desejo.marcarPlanejado();
        assert desejo.getEstado() == EstadoDesejo.PLANEJADO;
        RegistroMergulho registro = new RegistroMergulho(ana, perfil, LocalDateTime.of(2026,10,2,12,0), 30, 15);
        registro.atualizarDados(registro.getDataHora(),30,15,25.0,10.0,NivelSujeira.BAIXO,true,"Peixes","Título","Relato","Calmo");
        RegistroMergulho livre = new RegistroMergulho(ana,null,null,"Local pessoal",LocalDateTime.now(),20,8);
        assert livre.getPonto() == null;
        Colecao colecao = new Colecao(ana,"Viagem");
        colecao.adicionar(registro);
        colecao.adicionar(registro);
        assert colecao.getRegistros().size() == 1;
        falha(() -> new Colecao(bia,"Outra").adicionar(registro));
        Fotografia foto = new Fotografia("foto.jpg","Foto");
        assert registro.adicionarFoto(foto);
        registro.definirCapa(foto);
        assert foto.isCapa();
        falha(() -> livre.adicionarFoto(foto));
        falha(() -> registro.definirCapa(new Fotografia("fora.jpg","Fora")));
        for (int i=0;i<4;i++) assert registro.adicionarFoto(new Fotografia("foto"+i,""));
        assert !registro.adicionarFoto(new Fotografia("sexta",""));
        AvaliacaoDificuldade dificuldade = new AvaliacaoDificuldade(NivelDificuldade.MODERADA,"Comentário");
        dificuldade.atualizar(NivelDificuldade.DIFICIL,Set.of(FatorDificuldade.CORRENTEZA),"Atualizado");
        registro.setAvaliacao(dificuldade);
        assert NivelDificuldade.values().length == 5;
        List<RegistroMergulho> registros = new ArrayList<>(List.of(registro));
        List<Colecao> colecoes = new ArrayList<>(List.of(colecao));
        ServicoGamificacao gamificacao = new ServicoGamificacao(registros,colecoes);
        gamificacao.recalcular(ana);
        assert gamificacao.getCarimbos(ana).size() == 1;
        assert gamificacao.getConquistas(ana).size() == 3;
        LocalDateTime obtidaEm = gamificacao.getConquistas(ana).get(0).obtidaEm();
        gamificacao.recalcular(ana);
        assert gamificacao.getConquistas(ana).get(0).obtidaEm().equals(obtidaEm);
        RelatorioEstatistico relatorio = new ServicoEstatisticas(registros).calcular(ana,null,null);
        assert relatorio.totalMergulhos() == 1 && relatorio.mediaProfundidade() == 15;
        assert relatorio.percentualPeixes() == 100 && relatorio.pontosVisitados() == 1;
        assert new ServicoEstatisticas(registros).calcular(bia,null,null).totalMergulhos() == 0;
        colecao.remover(registro);
        assert registros.size() == 1;
        registros.clear();
        gamificacao.recalcular(ana);
        assert gamificacao.getCarimbos(ana).isEmpty() && gamificacao.getConquistas(ana).isEmpty();
        RelatorioEstatistico vazio = new ServicoEstatisticas(registros).calcular(ana,null,null);
        assert vazio.mediaProfundidade() == 0 && vazio.percentualPeixes() == 0;
        Sessao expirada = new Sessao(ana,Instant.EPOCH);
        assert !expirada.ativa(Instant.now());
        Sessao ativa = new Sessao(ana,Instant.now().plusSeconds(60));
        ativa.encerrar();
        assert !ativa.ativa(Instant.now());
        falha(() -> registro.atualizarDados(LocalDateTime.now(),Double.NaN,15,null,null,null,false,null,null,null,null));
        System.out.println("Testes da modelagem concluídos com sucesso.");
    }
    private static void falha(Runnable operacao) {
        try { operacao.run(); throw new AssertionError("Operação inválida foi aceita."); }
        catch (IllegalArgumentException e) { }
    }
}
