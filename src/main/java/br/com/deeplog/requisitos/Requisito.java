package br.com.deeplog.requisitos;

import br.com.deeplog.model.Mergulhador;

/**
 * Requisito de um perfil de mergulho (padrão Template Method).
 */
public abstract class Requisito {
    private final String descricao;
    private final String fonte;

    /**
     * Cria o requisito.
     *
     * @param descricao descrição do requisito
     * @param fonte fonte da informação
     */
    public Requisito(String descricao, String fonte) {
        this.descricao = descricao;
        this.fonte = fonte;
    }

    /**
     * Verifica o requisito (método template): confere os dados básicos e depois chama a verificação da subclasse.
     *
     * @param mergulhador mergulhador avaliado
     * @return resultado da verificação
     */
    public final Verificacao verificar(Mergulhador mergulhador) {
        if (!dadosBasicosValidos(mergulhador)) {
            return new Verificacao(EstadoVerificacao.INDETERMINADO, "Mergulhador não informado.");
        }
        return executarVerificacaoEspecifica(mergulhador);
    }
    
    /**
     * Confere se o mergulhador foi informado.
     *
     * @param mergulhador mergulhador avaliado
     * @return true se os dados são válidos
     */
    protected boolean dadosBasicosValidos(Mergulhador mergulhador) {
        return mergulhador != null;
    }
    
    /**
     * Parte específica da verificação, feita por cada subclasse.
     *
     * @param mergulhador mergulhador avaliado
     * @return resultado da verificação
     */
    protected abstract Verificacao executarVerificacaoEspecifica(Mergulhador mergulhador);

    public String getDescricao() {
        return descricao;
    }
    
    public String getFonte() {
        return fonte;
    }
}
