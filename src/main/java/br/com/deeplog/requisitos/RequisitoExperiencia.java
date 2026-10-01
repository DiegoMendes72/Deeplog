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
    public boolean verificar(Mergulhador mergulhador) {
        if (mergulhador == null) {
            return false;
        }
        return mergulhador.getQuantidadeMergulhos() >= quantidadeMinimaMergulhos;
    }

    public int getQuantidadeMinimaMergulhos() {
        return quantidadeMinimaMergulhos;
    }
}
