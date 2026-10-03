package br.com.deeplog.model;

import java.time.LocalDate;

public class CertificacaoObtida {
    private final java.util.UUID id = java.util.UUID.randomUUID();
    private Mergulhador titular;
    private Certificacao certificacao;
    private LocalDate dataConclusao;
    private String numeroRegistro;

    public CertificacaoObtida(Certificacao certificacao, LocalDate dataConclusao, String numeroRegistro) {
        if (certificacao == null) {
            throw new IllegalArgumentException("A certificação é obrigatória.");
        }
        this.certificacao = certificacao;
        this.dataConclusao = (dataConclusao != null) ? dataConclusao : LocalDate.now();
        this.numeroRegistro = numeroRegistro;
    }

    public Certificacao getCertificacao() {
        return certificacao;
    }
    void vincular(Mergulhador titular) {
        if (this.titular != null && this.titular != titular) throw new IllegalArgumentException("Certificação de outro usuário.");
        this.titular = titular;
    }
    public void atualizar(java.time.LocalDate dataConclusao, String numeroRegistro) {
        this.dataConclusao = java.util.Objects.requireNonNull(dataConclusao);
        this.numeroRegistro = numeroRegistro;
    }
    public java.util.UUID getId() { return id; }
    public Mergulhador getTitular() { return titular; }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }
}
