package br.com.deeplog.gamificacao;

public class Nivel {
    private int numero;
    private String titulo;
    private int pontosNecessarios;

    public Nivel(int numero, String titulo, int pontosNecessarios) {
        if (numero <= 0) {
            throw new IllegalArgumentException("O número do nível deve ser maior que zero.");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título do nível é obrigatório.");
        }
        if (pontosNecessarios < 0) {
            throw new IllegalArgumentException("Os pontos necessários não podem ser negativos.");
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
