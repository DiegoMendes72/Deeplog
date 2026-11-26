package br.com.deeplog.requisitos;
import java.util.List;
/**
 * Resultado da comparação entre mergulhador e perfil.
 */
public record ResultadoCompatibilidade(EstadoCompatibilidade estado, List<Verificacao> verificacoes) {
    public ResultadoCompatibilidade { verificacoes = List.copyOf(verificacoes); }
}
