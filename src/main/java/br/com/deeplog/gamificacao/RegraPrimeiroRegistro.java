package br.com.deeplog.gamificacao;

import br.com.deeplog.model.Mergulhador;
import br.com.deeplog.model.RegistroMergulho;
import java.util.List;

public class RegraPrimeiroRegistro implements RegraConquista {
    private List<RegistroMergulho> registros;

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
