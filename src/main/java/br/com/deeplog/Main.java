package br.com.deeplog;

import br.com.deeplog.enums.NivelDificuldade;
import br.com.deeplog.gamificacao.*;
import br.com.deeplog.model.*;
import br.com.deeplog.requisitos.RequisitoExperiencia;
import br.com.deeplog.autenticacao.ServicoAutenticacao;
import br.com.deeplog.autenticacao.ServicoRegistros;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== DEEPLOG - SISTEMA DE MERGULHO E GAMIFICAÇÃO ===");

        // 1. Criar Mergulhador
        ServicoAutenticacao autenticacao = new ServicoAutenticacao();
        Mergulhador mergulhador = autenticacao.cadastrar("Diego Mendes", "diego@deeplog.com", "senhaDeExemplo123");
        String sessao = autenticacao.login("diego@deeplog.com", "senhaDeExemplo123");
        ServicoRegistros registros = new ServicoRegistros(autenticacao);
        System.out.println("Mergulhador criado: " + mergulhador.getNome());

        // 2. Criar Ponto e Perfil de Mergulho
        PontoMergulho ponto = new PontoMergulho("Laje de Santos", "São Paulo, Brasil", "Excelente visibilidade", "Correnteza moderada");
        PerfilMergulho perfil = new PerfilMergulho(ponto, "Recreativo Avançado", 22.5);
        perfil.adicionarRequisito(new RequisitoExperiencia("Mínimo de 10 mergulhos", "Manual Padrão", 10));

        System.out.println("Ponto configurado: " + perfil.getPonto().getNome() + " | Profundidade máx: " + perfil.getProfundidadePrevistaM() + "m");

        // 3. Registrar um Mergulho
        java.util.UUID registroId = registros.registrar(sessao, perfil, LocalDateTime.now(), 45, 20.0);
        RegistroMergulho registro = registros.consultar(sessao, registroId);
        registro.setAvaliacao(new AvaliacaoDificuldade(NivelDificuldade.MODERADA, "Mergulho incrível!"));

        System.out.println("Mergulho registrado! Total de mergulhos do mergulhador: " + mergulhador.getQuantidadeMergulhos());

        // 4. Sistema de Gamificação
        Nivel nivel1 = new Nivel(1, "Mergulhador Iniciante", 0);
        Nivel nivel2 = new Nivel(2, "Explorador dos Mares", 100);

        ProgressoGamificacao progresso = new ProgressoGamificacao(mergulhador, nivel1);
        Conquista primeiraImersao = new Conquista("Primeira Imersão", "Registrou o primeiro mergulho no Deeplog", 50);
        
        progresso.desbloquearConquista(primeiraImersao);
        System.out.println("Conquista desbloqueada: " + primeiraImersao.getNome() + " (+ " + primeiraImersao.getPontosRecompensa() + " pontos)");
        System.out.println("Pontuação total atual: " + progresso.getPontosTotais() + " pts");

        // Atualizar Nível
        if (progresso.getPontosTotais() >= nivel2.getPontosNecessarios()) {
            progresso.setNivelAtual(nivel2);
        }
        System.out.println("Nível Atual: " + progresso.getNivelAtual().getTitulo());

        // compatibilidade e coleções
        mergulhador.atualizarPerfil(mergulhador.getNome(), 0);
        br.com.deeplog.requisitos.ResultadoCompatibilidade compatibilidade =
                new br.com.deeplog.requisitos.AvaliadorCompatibilidade().avaliar(mergulhador, perfil);
        System.out.println("Requisitos do perfil: " + compatibilidade.estado());
        Colecao colecao = new Colecao(mergulhador, "Primeiras aventuras");
        colecao.adicionar(registro);
        Fotografia foto = new Fotografia("exemplo.jpg", "Memória do mergulho");
        registro.adicionarFoto(foto);
        registro.definirCapa(foto);
        java.util.List<RegistroMergulho> historico = new java.util.ArrayList<>(registros.listar(sessao));
        ServicoGamificacao gamificacao = new ServicoGamificacao(historico, java.util.List.of(colecao));
        gamificacao.recalcular(mergulhador);
        System.out.println("Carimbos: " + gamificacao.getCarimbos(mergulhador).size());
        System.out.println("Conquistas por regras: " + gamificacao.getConquistas(mergulhador).size());
        br.com.deeplog.servicos.RelatorioEstatistico estatisticas =
                new br.com.deeplog.servicos.ServicoEstatisticas(historico).calcular(mergulhador, null, null);
        System.out.println("Tempo acumulado: " + estatisticas.tempoAcumulado() + " minutos");

        autenticacao.logout(sessao);
        System.out.println("Sessão encerrada.");
        System.out.println("\n=== TESTE CONCLUÍDO COM SUCESSO! ===");
    }
}
