package br.com.deeplog.requisitos;

import br.com.deeplog.excecoes.ValidacaoException;

public record Verificacao(EstadoVerificacao estado, String descricao) {
    public Verificacao {
        if (estado == null || descricao == null) throw new import ValidacaoException("Estado e descrição obrigatórios.");
    }
}
