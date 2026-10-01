package br.com.deeplog.model;

public class PontoMergulho {
    private String nome;
    private String localizacao;
    private String descricao;
    private String condicoesHabituais;

    public PontoMergulho(String nome, String localizacao, String descricao, String condicoesHabituais) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do ponto é obrigatório.");
        }
        this.nome = nome;
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.condicoesHabituais = condicoesHabituais;
    }

    public String getNome() {
        return nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCondicoesHabituais() {
        return condicoesHabituais;
    }
}
