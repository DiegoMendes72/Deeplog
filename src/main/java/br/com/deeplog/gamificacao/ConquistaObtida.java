package br.com.deeplog.gamificacao;
import br.com.deeplog.model.Mergulhador;
import java.time.LocalDateTime;
import java.util.Objects;
/**
 * Conquista liberada para um mergulhador.
 */
public record ConquistaObtida(Mergulhador usuario, Conquista conquista, LocalDateTime obtidaEm) {
    public ConquistaObtida { Objects.requireNonNull(usuario); Objects.requireNonNull(conquista); Objects.requireNonNull(obtidaEm); }
}
