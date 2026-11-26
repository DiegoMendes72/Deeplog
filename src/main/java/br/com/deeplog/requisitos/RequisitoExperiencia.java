package br.com.deeplog.requisitos;

import br.com.deeplog.model.Mergulhador;
import br.com.deeplog.excecoes.ValidacaoException;

/**
 * Requisito que exige uma quantidade mínima de mergulhos.
 */
public class RequisitoExperiencia extends Requisito {
    private int minimoMergulhos;

    /**
     * Cria o requisito.
     *
     * @param descricao descrição do requisito
     * @param fonte fonte da informação
     * @param minimoMergulhos quantidade mínima de mergulhos
     */
    public RequisitoExperiencia(String descricao, String fonte, int minimoMergulhos) {
        super(descricao, fonte);
        if (minimoMergulhos < 0) {
            throw new ValidacaoException("A quantidade mínima de mergulhos não pode ser negativa.");
        }
        this.minimoMergulhos = minimoMergulhos;
    }

    @Override
    protected Verificacao executarVerificacaoEspecifica(Mergulhador mergulhador) {
        if (mergulhador.getQuantidadeMergulhos() >= minimoMergulhos) {
            return new Verificacao(EstadoVerificacao.ATENDIDO, getDescricao());
        }
        if (mergulhador.getExperienciaAnterior() == null) {
            return new Verificacao(EstadoVerificacao.INDETERMINADO, "Experiência anterior não informada.");
        }
        long total = (long) mergulhador.getQuantidadeMergulhos() + mergulhador.getExperienciaAnterior();
        return new Verificacao(total >= minimoMergulhos ? EstadoVerificacao.ATENDIDO : EstadoVerificacao.PENDENTE, getDescricao());
    }

    public int getMinimoMergulhos() {
        return minimoMergulhos;
    }
}
