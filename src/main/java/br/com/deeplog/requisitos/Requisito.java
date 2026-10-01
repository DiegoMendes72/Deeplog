package br.com.deeplog.requisitos;

import br.com.deeplog.model.Mergulhador;
import java.time.LocalDate;

public abstract class Requisito {
    protected String descricao;
    protected String fonte;
    protected LocalDate revisadoEm;

    public Requisito(String descricao, String fonte) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do requisito é obrigatória.");
        }
        this.descricao = descricao;
        this.fonte = (fonte != null) ? fonte : "Manual Padrão";
        this.revisadoEm = LocalDate.now();
    }

    public abstract boolean verificar(Mergulhador mergulhador);

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
