package br.com.deeplog.gamificacao;

import br.com.deeplog.model.Mergulhador;
import br.com.deeplog.model.RegistroMergulho;
import java.util.List;

/**
 * Regra atendida quando o mergulhador tem pelo menos um registro.
 */
public class RegraPrimeiroRegistro implements RegraConquista {
    private List<RegistroMergulho> registros;

    /**
     * Cria a regra.
     *
     * @param registros registros de mergulho do sistema
     */
    public RegraPrimeiroRegistro(List<RegistroMergulho> registros) {
        this.registros = registros;
    }

    @Override
    public boolean atendida(Mergulhador usuario) {
        for (RegistroMergulho r : registros) {
            if (r.getAutor() == usuario) {
                return true;
            }
        }
        return false;
    }
}
