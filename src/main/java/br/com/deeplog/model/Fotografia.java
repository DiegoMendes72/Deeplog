package br.com.deeplog.model;

import java.time.LocalDateTime;

public class Fotografia {
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

    public String getLegenda() {
        return legenda;
    }

    public LocalDateTime getDataUpload() {
        return dataUpload;
    }
}
