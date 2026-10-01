package br.com.deeplog.gamificacao;

import java.time.LocalDate;

public class Desafio {
    private String titulo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int pontosRecompensa;

    public Desafio(String titulo, String descricao, LocalDate dataInicio, LocalDate dataFim, int pontosRecompensa) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título do desafio é obrigatório.");
        }
        if (dataInicio != null && dataFim != null && dataFim.isBefore(dataInicio)) {
            throw new IllegalArgumentException("A data de fim não pode ser anterior à data de início.");
        }
        if (pontosRecompensa < 0) {
            throw new IllegalArgumentException("Os pontos não podem ser negativos.");
        }
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = (dataInicio != null) ? dataInicio : LocalDate.now();
        this.dataFim = dataFim;
        this.pontosRecompensa = pontosRecompensa;
    }

    public boolean estaAtivo() {
        LocalDate hoje = LocalDate.now();
        if (dataFim == null) {
            return !hoje.isBefore(dataInicio);
        }
        return !hoje.isBefore(dataInicio) && !hoje.isAfter(dataFim);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public int getPontosRecompensa() {
        return pontosRecompensa;
    }
}
