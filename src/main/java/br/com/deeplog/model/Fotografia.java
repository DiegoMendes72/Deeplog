package br.com.deeplog.model;

import java.time.LocalDateTime;

public class Fotografia {
    private final java.util.UUID id = java.util.UUID.randomUUID();
    private RegistroMergulho registro;
    private String caminhoArquivo;
    private String legenda;
    private LocalDateTime dataUpload;

    public Fotografia(String caminhoArquivo, String legenda) {
        if (caminhoArquivo == null || caminhoArquivo.trim().isEmpty()) {
            throw new IllegalArgumentException("O caminho do arquivo é obrigatório.");
        }
        this.caminhoArquivo = caminhoArquivo;
        this.legenda = legenda;
        this.dataUpload = LocalDateTime.now();
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }
    void vincular(RegistroMergulho registro) {
        if (this.registro != null && this.registro != registro) throw new IllegalArgumentException("Foto já pertence a outro registro.");
        this.registro = registro;
    }
    public java.util.UUID getId() { return id; }
    public boolean isCapa() { return registro != null && registro.getCapa() == this; }

    public String getLegenda() {
        return legenda;
    }

    public LocalDateTime getDataUpload() {
        return dataUpload;
    }
}
