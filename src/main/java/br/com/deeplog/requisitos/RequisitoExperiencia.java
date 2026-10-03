package br.com.deeplog.requisitos;

import br.com.deeplog.model.Mergulhador;

public class RequisitoExperiencia extends Requisito {
    private int quantidadeMinimaMergulhos;

    public RequisitoExperiencia(String descricao, String fonte, int quantidadeMinimaMergulhos) {
        super(descricao, fonte);
        if (quantidadeMinimaMergulhos < 0) {
            throw new IllegalArgumentException("A quantidade mínima de mergulhos não pode ser negativa.");
        }
        this.quantidadeMinimaMergulhos = quantidadeMinimaMergulhos;
    }

    @Override
    public Verificacao verificar(Mergulhador mergulhador) {
        if (mergulhador == null) {
            return new Verificacao(EstadoVerificacao.INDETERMINADO, "Mergulhador não informado.");
        }
        if (mergulhador.getQuantidadeMergulhos() >= quantidadeMinimaMergulhos) {
            return new Verificacao(EstadoVerificacao.ATENDIDO, getDescricao());
        }
        if (mergulhador.getExperienciaAnterior() == null) {
            return new Verificacao(EstadoVerificacao.INDETERMINADO, "Experiência anterior não informada.");
        }
        long total = (long) mergulhador.getQuantidadeMergulhos() + mergulhador.getExperienciaAnterior();
        return new Verificacao(total >= quantidadeMinimaMergulhos ? EstadoVerificacao.ATENDIDO : EstadoVerificacao.PENDENTE, getDescricao());
    }

    public int getQuantidadeMinimaMergulhos() {
        return quantidadeMinimaMergulhos;
    }
}
