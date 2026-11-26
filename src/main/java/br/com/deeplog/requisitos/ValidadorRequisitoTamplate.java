package br.com.deeplog.requisitos;

import br.com.deeplog.model.Mergulhador;
import br.com.deeplog.model.PerfilMergulho;

public abstract class ValidadorRequisitoTemplate {

    public final boolean validar(Mergulhador mergulhador, PerfilMergulho perfil) {
        if (!preValidar(mergulhador, perfil)) {
            return false;
        }
      
        boolean atendeRegra = avaliarCriterioEspecifico(mergulhador, perfil);
        posValidar(atendeRegra);
        return atendeRegra;
    }

    protected boolean preValidar(Mergulhador mergulhador, PerfilMergulho perfil) {
        return mergulhador != null && perfil != null;
    }

    protected abstract boolean avaliarCriterioEspecifico(Mergulhador mergulhador, PerfilMergulho perfil);
  
    protected void posValidar(boolean resultado) {
        // Implementação padrão vazia
    }
}
