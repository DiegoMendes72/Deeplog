package br.com.deeplog.requisitos;
import java.util.List;
public record ResultadoCompatibilidade(EstadoCompatibilidade estado, List<Verificacao> verificacoes) {
    public ResultadoCompatibilidade { verificacoes = List.copyOf(verificacoes); }
}
