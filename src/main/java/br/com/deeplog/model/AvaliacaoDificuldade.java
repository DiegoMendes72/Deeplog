package br.com.deeplog.model;

import br.com.deeplog.enums.NivelDificuldade;
import java.time.LocalDateTime;

public class AvaliacaoDificuldade {
    private java.util.Set<br.com.deeplog.enums.FatorDificuldade> fatores = java.util.Set.of();
    private NivelDificuldade nivel;
    private String comentario;
    private LocalDateTime dataAvaliacao;

    public AvaliacaoDificuldade(NivelDificuldade nivel, String comentario) {
        if (nivel == null) {
            throw new IllegalArgumentException("O nível de dificuldade é obrigatório.");
        }
        this.nivel = nivel;
        this.comentario = comentario;
        this.dataAvaliacao = LocalDateTime.now();
    }

    public NivelDificuldade getNivel() {
        return nivel;
    }
    public void atualizar(NivelDificuldade nivel, java.util.Set<br.com.deeplog.enums.FatorDificuldade> fatores, String comentario) {
        if (nivel == null || fatores == null) throw new IllegalArgumentException("Nível e fatores obrigatórios.");
        java.util.Set<br.com.deeplog.enums.FatorDificuldade> copia = java.util.Set.copyOf(fatores);
        this.nivel = nivel;
        this.fatores = copia;
        this.comentario = comentario;
    }
    public java.util.Set<br.com.deeplog.enums.FatorDificuldade> getFatores() { return fatores; }

    public String getComentario() {
        return comentario;
    }

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }
}
