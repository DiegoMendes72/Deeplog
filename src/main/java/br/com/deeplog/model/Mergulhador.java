package br.com.deeplog.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import br.com.deeplog.autenticacao.Senhas;

public class Mergulhador {
    private String nome;
    private String email;
    private String senhaHash;
    private int quantidadeMergulhos;
    private final Set<CertificacaoObtida> certificacoes;

    public Mergulhador(String nome, String email, String senhaHash) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.quantidadeMergulhos = 0;
        this.certificacoes = new HashSet<>();
    }

    public void adicionarCertificacao(CertificacaoObtida certificacao) {
        if (certificacao != null) {
            this.certificacoes.add(certificacao);
        }
    }

    public void incrementarMergulhos() {
        this.quantidadeMergulhos++;
    }

    public boolean verificarSenha(String senha) {
        return Senhas.verificar(senha, senhaHash);
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getQuantidadeMergulhos() {
        return quantidadeMergulhos;
    }

    public Set<CertificacaoObtida> getCertificacoes() {
        return Collections.unmodifiableSet(certificacoes);
    }
}
