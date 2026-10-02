package br.com.deeplog;

import br.com.deeplog.enums.NivelDificuldade;
import br.com.deeplog.gamificacao.*;
import br.com.deeplog.model.*;
import br.com.deeplog.requisitos.RequisitoExperiencia;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== DEEPLOG - SISTEMA DE MERGULHO E GAMIFICAÇÃO ===");

        // 1. Criar Mergulhador
        Mergulhador mergulhador = new Mergulhador("Diego Mendes", "diego@deeplog.com", "hash12345");
        System.out.println("Mergulhador criado: " + mergulhador.getNome());

        // 2. Criar Ponto e Perfil de Mergulho
        PontoMergulho ponto = new PontoMergulho("Laje de Santos", "São Paulo, Brasil", "Excelente visibilidade", "Correnteza moderada");
        PerfilMergulho perfil = new PerfilMergulho(ponto, "Recreativo Avançado", 22.5);
        perfil.adicionarRequisito(new RequisitoExperiencia("Mínimo de 10 mergulhos", "Manual Padrão", 10));

        System.out.println("Ponto configurado: " + perfil.getPonto().getNome() + " | Profundidade máx: " + perfil.getProfundidadeMaxima() + "m");

        // 3. Registrar um Mergulho
        RegistroMergulho registro = new RegistroMergulho(mergulhador, perfil, LocalDateTime.now(), 45.0, 20.0);
        mergulhador.incrementarMergulhos();
        registro.setAvaliacao(new AvaliacaoDificuldade(NivelDificuldade.MODERADO, "Mergulho incrível!"));

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

        System.out.println("\n=== TESTE CONCLUÍDO COM SUCESSO! ===");
    }
}
