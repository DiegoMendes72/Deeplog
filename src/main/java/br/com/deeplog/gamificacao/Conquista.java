package br.com.deeplog.gamificacao;

public class Conquista {
    private String nome;
    private String descricao;
    private int pontosRecompensa;

    public Conquista(String nome, String descricao, int pontosRecompensa) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da conquista é obrigatório.");
        }
        if (pontosRecompensa < 0) {
            throw new IllegalArgumentException("Os pontos de recompensa não podem ser negativos.");
        }
        this.nome = nome;
        this.descricao = descricao;
        this.pontosRecompensa = pontosRecompensa;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getPontosRecompensa() {
        return pontosRecompensa;
    }
}
