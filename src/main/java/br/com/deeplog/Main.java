package br.com.deeplog;

import br.com.deeplog.enums.NivelDificuldade;
import br.com.deeplog.gamificacao.*;
import br.com.deeplog.model.*;
import br.com.deeplog.requisitos.RequisitoExperiencia;
import br.com.deeplog.autenticacao.ServicoAutenticacao;
import br.com.deeplog.autenticacao.ServicoRegistros;
import br.com.deeplog.persistencia.RepositorioArquivo;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== DEEPLOG - SISTEMA DE MERGULHO E GAMIFICAÇÃO ===");

        ServicoAutenticacao autenticacao = new ServicoAutenticacao();
        ServicoRegistros registros = new ServicoRegistros(autenticacao);

        // 1. Entrada de Dados do Mergulhador pelo Usuário
        System.out.println("\n--- CADASTRO DE MERGULHADOR ---");
        System.out.print("Digite seu nome completo: ");
        String nome = scanner.nextLine();

        System.out.print("Digite seu e-mail: ");
        String email = scanner.nextLine();

        System.out.print("Digite sua senha (mínimo de caracteres recomendado): ");
        String senha = scanner.nextLine();

        Mergulhador mergulhador = autenticacao.cadastrar(nome, email, senha);
        String sessao = autenticacao.login(email, senha);
        System.out.println("Mergulhador criado e logado com sucesso: " + mergulhador.getNome());

        // 2. Entrada de Dados do Ponto e Perfil de Mergulho
        System.out.println("\n--- CONFIGURAÇÃO DO PONTO DE MERGULHO ---");
        System.out.print("Nome do Ponto de Mergulho (ex: Laje de Santos): ");
        String nomePonto = scanner.nextLine();

        System.out.print("Localização (ex: São Paulo, Brasil): ");
        String localizacao = scanner.nextLine();

        System.out.print("Visibilidade (ex: Excelente): ");
        String visibilidade = scanner.nextLine();

        System.out.print("Correnteza (ex: Moderada): ");
        String correnteza = scanner.nextLine();

        PontoMergulho ponto = new PontoMergulho(nomePonto, localizacao, visibilidade, correnteza);
        
        System.out.print("Informe a profundidade prevista máxima (metros): ");
        double profundidadePrevista = Double.parseDouble(scanner.nextLine());

        PerfilMergulho perfil = new PerfilMergulho(ponto, "Recreativo Avançado", profundidadePrevista);
        perfil.adicionarRequisito(new RequisitoExperiencia("Mínimo de 10 mergulhos", "Manual Padrão", 10));

        System.out.println("Ponto configurado: " + perfil.getPonto().getNome() + " | Profundidade máx: " + perfil.getProfundidadePrevistaM() + "m");

        // 3. Registrar um Mergulho com dados interativos
        System.out.println("\n--- REGISTRAR MERGULHO ---");
        System.out.print("Informe a duração do mergulho (minutos): ");
        int duracao = Integer.parseInt(scanner.nextLine());

        System.out.print("Informe a profundidade atingida (metros): ");
        double profundidadeReal = Double.parseDouble(scanner.nextLine());

        java.util.UUID registroId = registros.registrar(sessao, perfil, LocalDateTime.now(), duracao, profundidadeReal);
        RegistroMergulho registro = registros.consultar(sessao, registroId);
        
        System.out.print("Digite uma avaliação/comentário sobre o mergulho: ");
        String comentario = scanner.nextLine();
        registro.setAvaliacao(new AvaliacaoDificuldade(NivelDificuldade.MODERADA, comentario));

        System.out.println("Mergulho registrado! Total de mergulhos do mergulhador: " + mergulhador.getQuantidadeMergulhos());

        // 4. Sistema de Gamificação (Automático baseado nos dados)
        Nivel nivel1 = new Nivel(1, "Mergulhador Iniciante", 0);
        Nivel nivel2 = new Nivel(2, "Explorador dos Mares", 100);

        ProgressoGamificacao progresso = new ProgressoGamificacao(mergulhador, nivel1);
        Conquista primeiraImersao = new Conquista("Primeira Imersão", "Registrou o primeiro mergulho no Deeplog", 50);
        
        progresso.desbloquearConquista(primeiraImersao);
        System.out.println("\n--- GAMIFICAÇÃO ---");
        System.out.println("Conquista desbloqueada: " + primeiraImersao.getNome() + " (+ " + primeiraImersao.getPontosRecompensa() + " pontos)");
        System.out.println("Pontuação total atual: " + progresso.getPontosTotais() + " pts");

        if (progresso.getPontosTotais() >= nivel2.getPontosNecessarios()) {
            progresso.setNivelAtual(nivel2);
        }
        System.out.println("Nível Atual: " + progresso.getNivelAtual().getTitulo());

        // Compatibilidade e coleções
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
        for (ConquistaObtida c : gamificacao.getConquistas(mergulhador)) {
            System.out.println("- " + c.conquista().getNome());
        }
        
        br.com.deeplog.servicos.RelatorioEstatistico estatisticas =
                new br.com.deeplog.servicos.ServicoEstatisticas(historico).calcular(mergulhador, null, null);
        System.out.println("Tempo acumulado: " + estatisticas.tempoAcumulado() + " minutos");

        // 5. Persistência em Arquivo (Leitura e Escrita)
        System.out.println("\n--- TESTE DE PERSISTÊNCIA EM ARQUIVO ---");
        RepositorioArquivo repositorio = new RepositorioArquivo("registros_mergulho.txt");
        
        repositorio.salvarRegistros(historico);
        System.out.println("Dados salvos com sucesso no arquivo 'registros_mergulho.txt'.");
        
        System.out.println("Lendo os dados do arquivo:");
        java.util.List<String> linhasLidas = repositorio.lerLinhas();
        for (String linha : linhasLidas) {
            System.out.println(linha);
        }

        // 6. Logout
        autenticacao.logout(sessao);
        System.out.println("\nSessão encerrada.");
        System.out.println("\n=== TESTE CONCLUÍDO COM SUCESSO! ===");
        
        scanner.close();
    }
}
