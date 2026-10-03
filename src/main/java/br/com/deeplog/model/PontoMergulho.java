package br.com.deeplog.model;

public class PontoMergulho {
    private final java.util.UUID id = java.util.UUID.randomUUID();
    private String nome;
    private String localizacao;
    private String descricao;
    private String caracteristicas;

    public PontoMergulho(String nome, String localizacao, String descricao, String caracteristicas) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do ponto é obrigatório.");
        }
        this.nome = nome;
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
    }

    public String getNome() {
        return nome;
    }
    public java.util.UUID getId() { return id; }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }
}
