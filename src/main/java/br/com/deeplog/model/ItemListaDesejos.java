package br.com.deeplog.model;
import br.com.deeplog.enums.EstadoDesejo;
import java.util.Objects;
import br.com.deeplog.excecoes.ValidacaoException;

/**
 * Destino de mergulho que o mergulhador quer visitar.
 */
public class ItemListaDesejos {
    private final Mergulhador usuario;
    private final PontoMergulho ponto;
    private final PerfilMergulho perfil;
    private EstadoDesejo estado = EstadoDesejo.DESEJADO;
    public ItemListaDesejos(Mergulhador usuario, PontoMergulho ponto, PerfilMergulho perfil) {
        this.usuario = Objects.requireNonNull(usuario);
        this.ponto = Objects.requireNonNull(ponto);
        if (perfil != null && perfil.getPonto() != ponto) throw new ValidacaoException("Perfil de outro ponto.");
        this.perfil = perfil;
    }
    /** Marca o destino como planejado. */
    public void marcarPlanejado() { estado = EstadoDesejo.PLANEJADO; }
    public Mergulhador getUsuario() { return usuario; }
    public PontoMergulho getPonto() { return ponto; }
    public PerfilMergulho getPerfil() { return perfil; }
    public EstadoDesejo getEstado() { return estado; }
}
