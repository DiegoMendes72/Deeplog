package br.com.deeplog.requisitos;
public record Verificacao(EstadoVerificacao estado, String descricao) {
    public Verificacao {
        if (estado == null || descricao == null) throw new IllegalArgumentException("Estado e descrição obrigatórios.");
    }
}
