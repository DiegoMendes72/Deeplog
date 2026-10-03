package br.com.deeplog.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import br.com.deeplog.autenticacao.Senhas;

public class Mergulhador {
    private final java.util.UUID id = java.util.UUID.randomUUID();
    private Integer experienciaAnterior;
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
            certificacao.vincular(this);
            this.certificacoes.add(certificacao);
        }
    }

    public void incrementarMergulhos() {
        this.quantidadeMergulhos++;
    }

    public boolean verificarSenha(String senha) {
        return Senhas.verificar(senha, senhaHash);
    }

    public java.util.UUID getId() { return id; }
    public Integer getExperienciaAnterior() { return experienciaAnterior; }
    public void atualizarPerfil(String nome, Integer experienciaAnterior) {
        if (nome == null || nome.isBlank() || (experienciaAnterior != null && experienciaAnterior < 0)) {
            throw new IllegalArgumentException("Nome e experiência inválidos.");
        }
        this.nome = nome.trim();
        this.experienciaAnterior = experienciaAnterior;
    }
    public void decrementarMergulhos() {
        if (quantidadeMergulhos > 0) quantidadeMergulhos--;
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
