package br.com.deeplog.enums;

public enum NivelDificuldade {
    FACIL("Fácil"),
    MODERADO("Moderado"),
    DIFACIL("Difícil"),
    EXTREMO("Extremo");

    private final String descricao;

    NivelDificuldade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
