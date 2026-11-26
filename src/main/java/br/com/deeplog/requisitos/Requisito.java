package br.com.deeplog.requisitos;

import br.com.deeplog.model.Mergulhador;

public abstract class Requisito {
    private final String descricao;
    private final String fonte;

    public Requisito(String descricao, String fonte) {
        this.descricao = descricao;
        this.fonte = fonte;
    }

    public final Verificacao verificar(Mergulhador mergulhador) {
        if (!dadosBasicosValidos(mergulhador)) {
            return new Verificacao(EstadoVerificacao.INDETERMINADO, "Mergulhador não informado.");
        }
        return executarVerificacaoEspecifica(mergulhador);
    }
    
    protected boolean dadosBasicosValidos(Mergulhador mergulhador) {
        return mergulhador != null;
    }
    
    protected abstract Verificacao executarVerificacaoEspecifica(Mergulhador mergulhador);

    public String getDescricao() {
        return descricao;
    }
    
    public String getFonte() {
        return fonte;
    }
}
