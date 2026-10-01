package br.com.deeplog.enums;

public enum StatusCompatibilidade {
    COMPATIVEL("Compatível - Atende a todos os requisitos"),
    PARCIAL("Parcialmente Compatível - Requer atenção em alguns requisitos"),
    INCOMPATIVEL("Incompatível - Não atende aos requisitos mínimos");

    private final String descricao;

    StatusCompatibilidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
