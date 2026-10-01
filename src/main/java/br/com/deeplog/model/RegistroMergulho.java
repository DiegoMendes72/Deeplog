package br.com.deeplog.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroMergulho {
    private Mergulhador mergulhador;
    private PerfilMergulho perfil;
    private LocalDateTime dataHora;
    private double duracaoMinutos;
    private double profundidadeAtingida;
    private final List<Fotografia> fotografias;
    private AvaliacaoDificuldade avaliacao;

    public RegistroMergulho(Mergulhador mergulhador, PerfilMergulho perfil, LocalDateTime dataHora, double duracaoMinutos, double profundidadeAtingida) {
        if (mergulhador == null || perfil == null) {
            throw new IllegalArgumentException("Mergulhador e Perfil de Mergulho são obrigatórios.");
        }
        if (duracaoMinutos <= 0) {
            throw new IllegalArgumentException("A duração deve ser maior que zero.");
        }
        this.mergulhador = mergulhador;
        this.perfil = perfil;
        this.dataHora = (dataHora != null) ? dataHora : LocalDateTime.now();
        this.duracaoMinutos = duracaoMinutos;
        this.profundidadeAtingida = profundidadeAtingida;
        this.fotografias = new ArrayList<>();
    }

    public boolean adicionarFotografia(Fotografia foto) {
        if (foto != null && fotografias.size() < 5) {
            fotografias.add(foto);
            return true;
        }
        return false;
    }

    public void setAvaliacao(AvaliacaoDificuldade avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Mergulhador getMergulhador() {
        return mergulhador;
    }

    public PerfilMergulho getPerfil() {
        return perfil;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public double getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public double getProfundidadeAtingida() {
        return profundidadeAtingida;
    }

    public List<Fotografia> getFotografias() {
        return Collections.unmodifiableList(fotografias);
    }

    public AvaliacaoDificuldade getAvaliacao() {
        return avaliacao;
    }
}
