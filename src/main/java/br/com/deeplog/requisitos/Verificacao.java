package br.com.deeplog.requisitos;

import br.com.deeplog.excecoes.ValidacaoException;

/**
 * Resultado da verificação de um requisito.
 */
public record Verificacao(EstadoVerificacao estado, String descricao) {
    public Verificacao {
        if (estado == null || descricao == null) throw new ValidacaoException("Estado e descrição obrigatórios.");
    }
}
