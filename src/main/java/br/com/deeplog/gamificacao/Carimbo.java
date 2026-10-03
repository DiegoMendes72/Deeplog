package br.com.deeplog.gamificacao;
import br.com.deeplog.model.*;
import java.time.LocalDate;
import java.util.Objects;
public record Carimbo(Mergulhador usuario, PontoMergulho ponto, LocalDate primeiraVisita) {
    public Carimbo { Objects.requireNonNull(usuario); Objects.requireNonNull(ponto); Objects.requireNonNull(primeiraVisita); }
}
