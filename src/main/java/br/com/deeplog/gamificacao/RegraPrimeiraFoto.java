package br.com.deeplog.gamificacao;

import br.com.deeplog.model.Mergulhador;
import br.com.deeplog.model.RegistroMergulho;
import java.util.List;

/**
 * Regra atendida quando o mergulhador tem um registro com fotografia.
 */
public class RegraPrimeiraFoto implements RegraConquista {
    private List<RegistroMergulho> registros;

    /**
     * Cria a regra.
     *
     * @param registros registros de mergulho do sistema
     */
    public RegraPrimeiraFoto(List<RegistroMergulho> registros) {
        this.registros = registros;
    }

    @Override
    public boolean atendida(Mergulhador usuario) {
        for (RegistroMergulho r : registros) {
            if (r.getAutor() == usuario && !r.getFotografias().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
