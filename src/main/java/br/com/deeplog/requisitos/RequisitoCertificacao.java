package br.com.deeplog.requisitos;

import br.com.deeplog.model.Certificacao;
import br.com.deeplog.model.CertificacaoObtida;
import br.com.deeplog.model.Mergulhador;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class RequisitoCertificacao extends Requisito {
    private final Set<Certificacao> certificacoesAceitas;

    public RequisitoCertificacao(String descricao, String fonte, Set<Certificacao> certificacoesAceitas) {
        super(descricao, fonte);
        if (certificacoesAceitas == null || certificacoesAceitas.isEmpty()) {
            throw new IllegalArgumentException("Ao menos uma certificação aceita deve ser informada.");
        }
        this.certificacoesAceitas = new HashSet<>(certificacoesAceitas);
    }

    @Override
    public Verificacao verificar(Mergulhador mergulhador) {
        if (mergulhador == null || mergulhador.getCertificacoes() == null) {
            return new Verificacao(EstadoVerificacao.INDETERMINADO, "Mergulhador não informado.");
        }

        for (CertificacaoObtida obtida : mergulhador.getCertificacoes()) {
            if (obtida != null && certificacoesAceitas.contains(obtida.getCertificacao())) {
                return new Verificacao(EstadoVerificacao.ATENDIDO, getDescricao());
            }
        }
        return new Verificacao(EstadoVerificacao.PENDENTE, getDescricao());
    }

    public Set<Certificacao> getCertificacoesAceitas() {
        return Collections.unmodifiableSet(certificacoesAceitas);
    }
}
