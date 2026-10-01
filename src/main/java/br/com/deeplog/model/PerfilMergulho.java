package br.com.deeplog.model;

import br.com.deeplog.requisitos.Requisito;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerfilMergulho {
    private PontoMergulho ponto;
    private String tipoMergulho;
    private double profundidadeMaxima;
    private final List<Requisito> requisitos;

    public PerfilMergulho(PontoMergulho ponto, String tipoMergulho, double profundidadeMaxima) {
        if (ponto == null) {
            throw new IllegalArgumentException("O ponto de mergulho é obrigatório.");
        }
        if (profundidadeMaxima <= 0) {
            throw new IllegalArgumentException("A profundidade máxima deve ser maior que zero.");
        }
        this.ponto = ponto;
        this.tipoMergulho = tipoMergulho;
        this.profundidadeMaxima = profundidadeMaxima;
        this.requisitos = new ArrayList<>();
    }

    public void adicionarRequisito(Requisito requisito) {
        if (requisito != null) {
            this.requisitos.add(requisito);
        }
    }

    public PontoMergulho getPonto() {
        return ponto;
    }

    public String getTipoMergulho() {
        return tipoMergulho;
    }

    public double getProfundidadeMaxima() {
        return profundidadeMaxima;
    }

    public List<Requisito> getRequisitos() {
        return Collections.unmodifiableList(requisitos);
    }
}
