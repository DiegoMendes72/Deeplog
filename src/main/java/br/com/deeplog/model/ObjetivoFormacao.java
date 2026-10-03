package br.com.deeplog.model;
import br.com.deeplog.enums.EstadoObjetivo;
import java.util.*;
public class ObjetivoFormacao {
    private final Mergulhador usuario;
    private final Certificacao certificacao;
    private EstadoObjetivo estado = EstadoObjetivo.DESEJADO;
    private final Set<PerfilMergulho> perfisInteresse = new LinkedHashSet<>();
    public ObjetivoFormacao(Mergulhador usuario, Certificacao certificacao) {
        this.usuario = Objects.requireNonNull(usuario);
        this.certificacao = Objects.requireNonNull(certificacao);
    }
    public void atualizarEstado(EstadoObjetivo estado) { this.estado = Objects.requireNonNull(estado); }
    public void adicionarPerfilInteresse(PerfilMergulho perfil) { perfisInteresse.add(Objects.requireNonNull(perfil)); }
    public Mergulhador getUsuario() { return usuario; }
    public Certificacao getCertificacao() { return certificacao; }
    public EstadoObjetivo getEstado() { return estado; }
    public Set<PerfilMergulho> getPerfisInteresse() { return Collections.unmodifiableSet(perfisInteresse); }
}
