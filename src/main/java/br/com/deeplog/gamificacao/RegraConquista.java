package br.com.deeplog.gamificacao;

import br.com.deeplog.model.Mergulhador;

/**
 * Estratégia que define quando uma conquista é liberada (padrão Strategy).
 */
public interface RegraConquista {

    /**
     * Verifica se o mergulhador cumpriu a regra.
     *
     * @param usuario mergulhador avaliado
     * @return true se a regra foi atendida
     */
    boolean atendida(Mergulhador usuario);
}
