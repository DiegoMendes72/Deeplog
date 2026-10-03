package br.com.deeplog.model;

import java.time.LocalDate;

public class CertificacaoObtida {
    private final java.util.UUID id = java.util.UUID.randomUUID();
    private Mergulhador titular;
    private Certificacao certificacao;
    private LocalDate dataObtencao;
    private String numeroRegistro;

    public CertificacaoObtida(Certificacao certificacao, LocalDate dataObtencao, String numeroRegistro) {
        if (certificacao == null) {
            throw new IllegalArgumentException("A certificação é obrigatória.");
        }
        this.certificacao = certificacao;
        this.dataObtencao = (dataObtencao != null) ? dataObtencao : LocalDate.now();
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
        this.dataObtencao = java.util.Objects.requireNonNull(dataConclusao);
        this.numeroRegistro = numeroRegistro;
    }
    public java.util.UUID getId() { return id; }
    public Mergulhador getTitular() { return titular; }
    public java.time.LocalDate getDataConclusao() { return dataObtencao; }

    public LocalDate getDataObtencao() {
        return dataObtencao;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }
}
