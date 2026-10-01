package br.com.deeplog;

import br.com.deeplog.model.*;
import br.com.deeplog.requisitos.*;
import br.com.deeplog.enums.NivelDificuldade;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Iniciando Validação das Classes do DeepLog ===");

        // 1. Instanciação de Mergulhador
        Mergulhador mergulhador = new Mergulhador("Carlos Silva", "carlos@deeplog.com", "hash123");
        
        // 2. Instanciação de Ponto e Perfil
        PontoMergulho ponto = new PontoMergulho("Laje de Santos", "Santos - SP", "Excelente ponto de mergulho", "Correnteza moderada");
        PerfilMergulho perfil = new PerfilMergulho(ponto, "Mergulho de Profundidade", 30.0);

        // 3. Adição de Requisito de Experiência (Polimorfismo)
        Requisito reqExp = new RequisitoExperiencia("Mínimo de 10 mergulhos", "PADI", 10);
        perfil.adicionarRequisito(reqExp);

        // 4. Criação do Registro de Mergulho
        RegistroMergulho registro = new RegistroMergulho(mergulhador, LocalDateTime.now(), 45, 28.5);
        
        // Teste de Limite de Fotos (Encapsulamento de Regra de Negócio)
        for (int i = 1; i <= 6; i++) {
            boolean adicionou = registro.adicionarFotografia(new Fotografia("caminho/foto" + i + ".jpg", "Foto " + i));
            System.out.println("Tentativa foto " + i + ": " + (adicionou ? "Sucesso" : "Recusada (Limite Atingido)"));
        }

        // 5. Avaliação de Dificuldade
        AvaliacaoDificuldade avaliacao = new AvaliacaoDificuldade(NivelDificuldade.MODERADO, "Boa visibilidade");
        registro.setAvaliacao(avaliacao);

        System.out.println("\n✅ Projeto DeepLog executado e validado com sucesso!");
    }
}
