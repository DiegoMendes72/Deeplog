package br.com.deeplog.excecoes;

/**
 * Exceção base do sistema.
 */
public class DeeplogException extends RuntimeException {
    public DeeplogException(String mensagem) {
        super(mensagem);
    }
    public DeeplogException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
