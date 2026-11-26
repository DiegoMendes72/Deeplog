package br.com.deeplog.excecoes;

/**
 * Erro quando o usuário acessa algo que não é dele.
 */
public class AcessoNegadoException extends DeeplogException {
    public AcessoNegadoException(String mensagem) {
        super(mensagem);
    }
}
