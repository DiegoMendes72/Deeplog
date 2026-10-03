package br.com.deeplog.model;

import java.time.LocalDateTime;

public class Fotografia {
    private final java.util.UUID id = java.util.UUID.randomUUID();
    private RegistroMergulho registro;
    private String caminho;
    private String legenda;
    private LocalDateTime dataUpload;

    public Fotografia(String caminho, String legenda) {
        if (caminho == null || caminho.trim().isEmpty()) {
            throw new IllegalArgumentException("O caminho do arquivo é obrigatório.");
        }
        this.caminho = caminho;
        this.legenda = legenda;
        this.dataUpload = LocalDateTime.now();
    }

    public String getCaminho() {
        return caminho;
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
