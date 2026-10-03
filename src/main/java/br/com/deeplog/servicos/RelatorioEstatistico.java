package br.com.deeplog.servicos;
import br.com.deeplog.enums.NivelDificuldade;
import java.time.YearMonth;
import java.util.Map;
public record RelatorioEstatistico(int totalMergulhos, double tempoAcumulado, double mediaProfundidade,
        double maiorProfundidade, double percentualPeixes, long pontosVisitados,
        Map<YearMonth, Long> frequencia, Map<NivelDificuldade, Long> dificuldades) {
    public RelatorioEstatistico { frequencia = Map.copyOf(frequencia); dificuldades = Map.copyOf(dificuldades); }
}
