package br.com.deeplog.autenticacao;
import br.com.deeplog.model.Mergulhador;
import java.time.Instant;
import java.util.*;
public class Sessao {
    private final UUID id = UUID.randomUUID();
    private final Mergulhador usuario;
    private final Instant expiraEm;
    private boolean encerrada;
    public Sessao(Mergulhador usuario, Instant expiraEm) {
        this.usuario = Objects.requireNonNull(usuario);
        this.expiraEm = Objects.requireNonNull(expiraEm);
    }
    public boolean ativa(Instant agora) { return !encerrada && agora.isBefore(expiraEm); }
    public void encerrar() { encerrada = true; }
    public UUID getId() { return id; }
    public Mergulhador getUsuario() { return usuario; }
    public Instant getExpiraEm() { return expiraEm; }
}
