package br.com.deeplog.requisitos;
import br.com.deeplog.model.*;
import java.util.*;
/**
 * Compara os requisitos do perfil com o mergulhador.
 */
public class AvaliadorCompatibilidade {
    /** Verifica todos os requisitos do perfil. */
    public ResultadoCompatibilidade avaliar(Mergulhador usuario, PerfilMergulho perfil) {
        Objects.requireNonNull(perfil);
        List<Verificacao> verificacoes = new ArrayList<>();
        for (Requisito requisito : perfil.getRequisitos()) verificacoes.add(requisito.verificar(usuario));
        EstadoCompatibilidade estado = EstadoCompatibilidade.ATENDIDO;
        if (verificacoes.isEmpty() || verificacoes.stream().anyMatch(v -> v.estado() == EstadoVerificacao.INDETERMINADO)) {
            estado = EstadoCompatibilidade.INDETERMINADO;
        }
        if (verificacoes.stream().anyMatch(v -> v.estado() == EstadoVerificacao.PENDENTE)) estado = EstadoCompatibilidade.PENDENTE;
        return new ResultadoCompatibilidade(estado, verificacoes);
    }
}
