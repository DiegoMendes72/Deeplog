package br.com.deeplog.excecoes;

/**
 * Erro de dados inválidos.
 */
public class ValidacaoException extends DeeplogException {
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
