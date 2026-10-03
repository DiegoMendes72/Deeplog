package br.com.deeplog.requisitos;

import br.com.deeplog.model.Mergulhador;
import java.time.LocalDate;

public abstract class Requisito {
    private String descricao;
    private String fonte;
    private LocalDate revisadoEm;

    public Requisito(String descricao, String fonte) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do requisito é obrigatória.");
        }
        this.descricao = descricao;
        this.fonte = (fonte != null) ? fonte : "Manual Padrão";
        this.revisadoEm = LocalDate.now();
    }

    public abstract Verificacao verificar(Mergulhador mergulhador);

    public String getDescricao() {
        return descricao;
    }

    public String getFonte() {
        return fonte;
    }

    public LocalDate getRevisadoEm() {
        return revisadoEm;
    }
}
