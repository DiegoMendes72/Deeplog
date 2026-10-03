package br.com.deeplog.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroMergulho {
    private final java.util.UUID id = java.util.UUID.randomUUID();
    private PontoMergulho ponto;
    private String localInformado;
    private Double temperaturaC;
    private Double visibilidadeM;
    private br.com.deeplog.enums.NivelSujeira sujeira;
    private boolean temPeixes;
    private String faunaObservada;
    private String titulo;
    private String relato;
    private String condicoes;
    private Fotografia capa;
    private Mergulhador autor;
    private PerfilMergulho perfil;
    private LocalDateTime dataHora;
    private int duracaoMin;
    private double profundidadeMaximaM;
    private final List<Fotografia> fotografias;
    private AvaliacaoDificuldade avaliacao;

    public RegistroMergulho(Mergulhador autor, PerfilMergulho perfil, LocalDateTime dataHora, int duracaoMin, double profundidadeMaximaM) {
        if (autor == null || perfil == null) {
            throw new IllegalArgumentException("Mergulhador e Perfil de Mergulho são obrigatórios.");
        }
        if (duracaoMin <= 0
                || !Double.isFinite(profundidadeMaximaM) || profundidadeMaximaM < 0) {
            throw new IllegalArgumentException("A duração deve ser maior que zero.");
        }
        this.autor = autor;
        this.perfil = perfil;
        this.ponto = perfil.getPonto();
        this.dataHora = (dataHora != null) ? dataHora : LocalDateTime.now();
        this.duracaoMin = duracaoMin;
        this.profundidadeMaximaM = profundidadeMaximaM;
        this.fotografias = new ArrayList<>();
    }

    public boolean adicionarFoto(Fotografia foto) {
        if (foto != null && !fotografias.contains(foto) && fotografias.size() < 5) {
            foto.vincular(this);
            fotografias.add(foto);
            return true;
        }
        return false;
    }

    public RegistroMergulho(Mergulhador autor, PontoMergulho ponto, PerfilMergulho perfil,
            String localInformado, LocalDateTime data, int duracao, double profundidade) {
        if (autor == null || (ponto == null && (localInformado == null || localInformado.isBlank()))
                || (perfil != null && perfil.getPonto() != ponto)) {
            throw new IllegalArgumentException("Autor, local e perfil incompatíveis.");
        }
        this.autor = autor;
        this.ponto = ponto;
        this.perfil = perfil;
        this.localInformado = localInformado;
        this.fotografias = new ArrayList<>();
        atualizarDados(data, duracao, profundidade, null, null, null, false, null, null, null, null);
    }

    public void atualizarDados(LocalDateTime data, int duracao, double profundidade,
            Double temperatura, Double visibilidade, br.com.deeplog.enums.NivelSujeira sujeira,
            boolean peixes, String fauna, String titulo, String relato, String condicoes) {
        if (data == null || duracao <= 0
                || !Double.isFinite(profundidade) || profundidade < 0
                || (temperatura != null && !Double.isFinite(temperatura))
                || (visibilidade != null && (!Double.isFinite(visibilidade) || visibilidade < 0))) {
            throw new IllegalArgumentException("Dados de mergulho inválidos.");
        }
        this.dataHora = data;
        this.duracaoMin = duracao;
        this.profundidadeMaximaM = profundidade;
        this.temperaturaC = temperatura;
        this.visibilidadeM = visibilidade;
        this.sujeira = sujeira;
        this.temPeixes = peixes;
        this.faunaObservada = fauna;
        this.titulo = titulo;
        this.relato = relato;
        this.condicoes = condicoes;
    }
    public void definirCapa(Fotografia foto) {
        if (foto == null || !fotografias.contains(foto)) throw new IllegalArgumentException("A capa deve pertencer ao registro.");
        this.capa = foto;
    }
    public java.util.UUID getId() { return id; }
    public PontoMergulho getPonto() { return ponto; }
    public String getLocalInformado() { return localInformado; }
    public Double getTemperaturaC() { return temperaturaC; }
    public Double getVisibilidadeM() { return visibilidadeM; }
    public br.com.deeplog.enums.NivelSujeira getSujeira() { return sujeira; }
    public boolean isTemPeixes() { return temPeixes; }
    public String getFaunaObservada() { return faunaObservada; }
    public String getTitulo() { return titulo; }
    public String getRelato() { return relato; }
    public String getCondicoes() { return condicoes; }
    public Fotografia getCapa() { return capa; }

    public void setAvaliacao(AvaliacaoDificuldade avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Mergulhador getAutor() {
        return autor;
    }

    public PerfilMergulho getPerfil() {
        return perfil;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public int getDuracaoMin() {
        return duracaoMin;
    }

    public double getProfundidadeMaximaM() {
        return profundidadeMaximaM;
    }

    public List<Fotografia> getFotografias() {
        return Collections.unmodifiableList(fotografias);
    }

    public AvaliacaoDificuldade getAvaliacao() {
        return avaliacao;
    }
}
