package br.com.deeplog.gamificacao;

import br.com.deeplog.excecoes.ValidacaoException;

public class Nivel {
    private int numero;
    private String titulo;
    private int pontosNecessarios;

    public Nivel(int numero, String titulo, int pontosNecessarios) {
        if (numero <= 0) {
            throw new import ValidacaoException("O número do nível deve ser maior que zero.");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new import ValidacaoException("O título do nível é obrigatório.");
        }
        if (pontosNecessarios < 0) {
            throw new import ValidacaoException("Os pontos necessários não podem ser negativos.");
        }
        this.numero = numero;
        this.titulo = titulo;
        this.pontosNecessarios = pontosNecessarios;
    }

    public int getNumero() {
        return numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPontosNecessarios() {
        return pontosNecessarios;
    }
} 
