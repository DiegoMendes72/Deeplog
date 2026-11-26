package br.com.deeplog.gamificacao;

import br.com.deeplog.model.Colecao;
import br.com.deeplog.model.Mergulhador;
import java.util.List;

public class RegraPrimeiraColecao implements RegraConquista {
    private List<Colecao> colecoes;

    public RegraPrimeiraColecao(List<Colecao> colecoes) {
        this.colecoes = colecoes;
    }

    @Override
    public boolean atendida(Mergulhador usuario) {
        for (Colecao c : colecoes) {
            if (c.getProprietario() == usuario && !c.getRegistros().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
