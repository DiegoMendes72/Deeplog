package br.com.deeplog.gamificacao;
import br.com.deeplog.model.*;
import java.time.LocalDateTime;
import java.util.*;
public class ServicoGamificacao {
    private final List<RegistroMergulho> registros;
    private final List<Colecao> colecoes;
    private final List<Conquista> conquistas;
    private final Map<Mergulhador, List<Carimbo>> carimbos = new HashMap<>();
    private final Map<Mergulhador, List<ConquistaObtida>> obtidas = new HashMap<>();
    public ServicoGamificacao(List<RegistroMergulho> registros, List<Colecao> colecoes) {
        this.registros = Objects.requireNonNull(registros);
        this.colecoes = Objects.requireNonNull(colecoes);
        conquistas = List.of(
            new Conquista("Primeiro registro", "Registrou um mergulho", u -> registros.stream().anyMatch(r -> r.getAutor() == u)),
            new Conquista("Primeira foto", "Registrou uma fotografia", u -> registros.stream().anyMatch(r -> r.getAutor() == u && !r.getFotografias().isEmpty())),
            new Conquista("Primeira coleção", "Organizou uma coleção", u -> colecoes.stream().anyMatch(c -> c.getProprietario() == u && c.getRegistros().stream().anyMatch(registros::contains)))
        );
    }
    public void recalcular(Mergulhador usuario) {
        Objects.requireNonNull(usuario);
        Map<PontoMergulho, java.time.LocalDate> visitas = new LinkedHashMap<>();
        for (RegistroMergulho r : registros) {
            if (r.getAutor() == usuario && r.getPonto() != null) {
                visitas.merge(r.getPonto(), r.getDataHora().toLocalDate(), (a,b) -> a.isBefore(b) ? a : b);
            }
        }
        List<Carimbo> novos = new ArrayList<>();
        visitas.forEach((p,d) -> novos.add(new Carimbo(usuario,p,d)));
        carimbos.put(usuario, novos);
        Map<Conquista, ConquistaObtida> anteriores = new HashMap<>();
        obtidas.getOrDefault(usuario, List.of()).forEach(c -> anteriores.put(c.conquista(), c));
        List<ConquistaObtida> atuais = new ArrayList<>();
        for (Conquista c : conquistas) if (c.getRegra().atendida(usuario)) {
            atuais.add(anteriores.containsKey(c) ? anteriores.get(c) : new ConquistaObtida(usuario,c,LocalDateTime.now()));
        }
        obtidas.put(usuario, atuais);
    }
    public List<Carimbo> getCarimbos(Mergulhador usuario) { return List.copyOf(carimbos.getOrDefault(usuario,List.of())); }
    public List<ConquistaObtida> getConquistas(Mergulhador usuario) { return List.copyOf(obtidas.getOrDefault(usuario,List.of())); }
}
