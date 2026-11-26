package br.com.deeplog.gamificacao;

import br.com.deeplog.model.Mergulhador;
import br.com.deeplog.model.RegistroMergulho;
import java.util.List;

/**
 * Regra atendida quando o mergulhador passa de uma profundidade mínima.
 */
public class RegraMergulhoProfundo implements RegraConquista {
    private List<RegistroMergulho> registros;
    private double profundidadeMinima;

    /**
     * Cria a regra.
     *
     * @param registros registros de mergulho do sistema
     * @param profundidadeMinima profundidade em metros
     */
    public RegraMergulhoProfundo(List<RegistroMergulho> registros, double profundidadeMinima) {
        this.registros = registros;
        this.profundidadeMinima = profundidadeMinima;
    }

    @Override
    public boolean atendida(Mergulhador usuario) {
        for (RegistroMergulho r : registros) {
            if (r.getAutor() == usuario && r.getProfundidadeMaximaM() >= profundidadeMinima) {
                return true;
            }
        }
        return false;
    }
}
