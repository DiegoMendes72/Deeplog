package br.com.deeplog.enums;

public enum NivelDificuldade {
    MUITO_FACIL("Muito fácil"),
    FACIL("Fácil"),
    MODERADA("Moderada"),
    DIFICIL("Difícil"),
    MUITO_DIFICIL("Muito difícil");

    private final String descricao;

    NivelDificuldade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
