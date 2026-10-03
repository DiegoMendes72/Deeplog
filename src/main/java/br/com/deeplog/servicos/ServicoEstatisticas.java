package br.com.deeplog.servicos;
import br.com.deeplog.model.*;
import br.com.deeplog.enums.NivelDificuldade;
import java.time.*;
import java.util.*;
public class ServicoEstatisticas {
    private final List<RegistroMergulho> registros;
    public ServicoEstatisticas(List<RegistroMergulho> registros) { this.registros = Objects.requireNonNull(registros); }
    public RelatorioEstatistico calcular(Mergulhador usuario, LocalDate inicio, LocalDate fim) {
        Objects.requireNonNull(usuario);
        if (inicio != null && fim != null && fim.isBefore(inicio)) throw new IllegalArgumentException("Período inválido.");
        List<RegistroMergulho> selecionados = registros.stream().filter(r -> r.getAutor() == usuario)
            .filter(r -> inicio == null || !r.getDataHora().toLocalDate().isBefore(inicio))
            .filter(r -> fim == null || !r.getDataHora().toLocalDate().isAfter(fim)).toList();
        Map<YearMonth,Long> frequencia = new HashMap<>();
        Map<NivelDificuldade,Long> dificuldades = new EnumMap<>(NivelDificuldade.class);
        for (RegistroMergulho r : selecionados) {
            frequencia.merge(YearMonth.from(r.getDataHora()),1L,Long::sum);
            if (r.getAvaliacao() != null) dificuldades.merge(r.getAvaliacao().getNivel(),1L,Long::sum);
        }
        return new RelatorioEstatistico(selecionados.size(), selecionados.stream().mapToDouble(RegistroMergulho::getDuracaoMin).sum(),
            selecionados.stream().mapToDouble(RegistroMergulho::getProfundidadeMaximaM).average().orElse(0),
            selecionados.stream().mapToDouble(RegistroMergulho::getProfundidadeMaximaM).max().orElse(0),
            selecionados.isEmpty() ? 0 : 100.0 * selecionados.stream().filter(RegistroMergulho::isTemPeixes).count() / selecionados.size(),
            selecionados.stream().map(RegistroMergulho::getPonto).filter(Objects::nonNull).distinct().count(), frequencia,dificuldades);
    }
}
