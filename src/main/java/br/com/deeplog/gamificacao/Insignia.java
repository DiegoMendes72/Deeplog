package br.com.deeplog.gamificacao;

import br.com.deeplog.excecoes.ValidacaoException;

public class Insignia {
    private String nome;
    private String iconeUrl;
    private String criterioConcessao;

    public Insignia(String nome, String iconeUrl, String criterioConcessao) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidacaoException("O nome da insígnia é obrigatório.");
        }
        this.nome = nome;
        this.iconeUrl = iconeUrl;
        this.criterioConcessao = criterioConcessao;
    }

    public String getNome() {
        return nome;
    }

    public String getIconeUrl() {
        return iconeUrl;
    }

    public String getCriterioConcessao() {
        return criterioConcessao;
    }
} 
