package br.com.deeplog.excecoes;

/**
 * Erro ao ler ou salvar arquivos.
 */
public class PersistenciaException extends DeeplogException {
    public PersistenciaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
