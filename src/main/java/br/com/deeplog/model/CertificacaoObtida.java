package br.com.deeplog.model;

import java.time.LocalDate;

public class CertificacaoObtida {
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

    public LocalDate getDataObtencao() {
        return dataObtencao;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }
}
