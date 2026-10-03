package br.com.deeplog.gamificacao;

public class Conquista {
    private final java.util.UUID id = java.util.UUID.randomUUID();
    private RegraConquista regra;
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
    public Conquista(String nome, String descricao, RegraConquista regra) {
        this(nome, descricao, 0);
        this.regra = java.util.Objects.requireNonNull(regra);
    }
    public java.util.UUID getId() { return id; }
    public RegraConquista getRegra() { return regra == null ? u -> false : regra; }

    public String getDescricao() {
        return descricao;
    }

    public int getPontosRecompensa() {
        return pontosRecompensa;
    }
}
