package br.com.deeplog.gamificacao;

import br.com.deeplog.model.Mergulhador;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ProgressoGamificacao {
    private Mergulhador mergulhador;
    private int pontosTotais;
    private Nivel nivelAtual;
    private final Set<Conquista> conquistasDesbloqueadas;
    private final Set<Insignia> insigniasObtidas;

    public ProgressoGamificacao(Mergulhador mergulhador, Nivel nivelInicial) {
        if (mergulhador == null) {
            throw new IllegalArgumentException("O mergulhador é obrigatório.");
        }
        this.mergulhador = mergulhador;
        this.nivelAtual = nivelInicial;
        this.pontosTotais = 0;
        this.conquistasDesbloqueadas = new HashSet<>();
        this.insigniasObtidas = new HashSet<>();
    }

    public void adicionarPontos(int pontos) {
        if (pontos > 0) {
            this.pontosTotais += pontos;
        }
    }

    public void desbloquearConquista(Conquista conquista) {
        if (conquista != null && conquistasDesbloqueadas.add(conquista)) {
            adicionarPontos(conquista.getPontosRecompensa());
        }
    }

    public void concederInsignia(Insignia insignia) {
        if (insignia != null) {
            this.insigniasObtidas.add(insignia);
        }
    }

    public void setNivelAtual(Nivel nivel) {
        if (nivel != null) {
            this.nivelAtual = nivel;
        }
    }

    public Mergulhador getMergulhador() {
        return mergulhador;
    }

    public int getPontosTotais() {
        return pontosTotais;
    }

    public Nivel getNivelAtual() {
        return nivelAtual;
    }

    public Set<Conquista> getConquistasDesbloqueadas() {
        return Collections.unmodifiableSet(conquistasDesbloqueadas);
    }

    public Set<Insignia> getInsigniasObtidas() {
        return Collections.unmodifiableSet(insigniasObtidas);
    }
}
