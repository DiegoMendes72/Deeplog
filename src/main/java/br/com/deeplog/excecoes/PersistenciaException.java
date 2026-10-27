package br.com.deeplog.excecoes;

public class PersistenciaException extends DeeplogException {
    public PersistenciaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
