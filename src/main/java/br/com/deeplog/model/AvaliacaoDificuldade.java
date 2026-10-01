package br.com.deeplog.model;

import br.com.deeplog.enums.NivelDificuldade;
import java.time.LocalDateTime;

public class AvaliacaoDificuldade {
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

    public String getComentario() {
        return comentario;
    }

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }
}
