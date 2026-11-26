package br.com.deeplog.excecoes;

/**
 * Erro de login ou de sessão.
 */
public class AutenticacaoException extends DeeplogException {
    public AutenticacaoException(String mensagem) {
        super(mensagem);
    }
}
