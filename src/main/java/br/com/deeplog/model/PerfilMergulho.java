package br.com.deeplog.model;

import br.com.deeplog.requisitos.Requisito;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerfilMergulho {
    private final java.util.UUID id = java.util.UUID.randomUUID();
    private br.com.deeplog.enums.FormaEntrada formaEntrada = br.com.deeplog.enums.FormaEntrada.OUTRA;
    private PontoMergulho ponto;
    private String nome;
    private double profundidadePrevistaM;
    private final List<Requisito> requisitos;

    public PerfilMergulho(PontoMergulho ponto, String nome, double profundidadePrevistaM) {
        if (ponto == null) {
            throw new IllegalArgumentException("O ponto de mergulho é obrigatório.");
        }
        if (profundidadePrevistaM <= 0) {
            throw new IllegalArgumentException("A profundidade máxima deve ser maior que zero.");
        }
        this.ponto = ponto;
        this.nome = nome;
        this.profundidadePrevistaM = profundidadePrevistaM;
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
    public java.util.UUID getId() { return id; }
    public br.com.deeplog.enums.FormaEntrada getFormaEntrada() { return formaEntrada; }
    public void setFormaEntrada(br.com.deeplog.enums.FormaEntrada entrada) { formaEntrada = java.util.Objects.requireNonNull(entrada); }

    public String getNome() {
        return nome;
    }

    public double getProfundidadePrevistaM() {
        return profundidadePrevistaM;
    }

    public List<Requisito> getRequisitos() {
        return Collections.unmodifiableList(requisitos);
    }
}
