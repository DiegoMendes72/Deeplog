package br.com.deeplog.autenticacao;

import br.com.deeplog.model.Mergulhador;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class ServicoAutenticacao {
    private final Map<String, Mergulhador> usuarios = new HashMap<>();
    private final Map<String, Sessao> sessoes = new HashMap<>();
    private final java.time.Clock relogio;
    private final java.time.Duration duracaoSessao;

    public ServicoAutenticacao() { this(java.time.Clock.systemUTC(), java.time.Duration.ofHours(8)); }
    public ServicoAutenticacao(java.time.Clock relogio, java.time.Duration duracaoSessao) {
        this.relogio = java.util.Objects.requireNonNull(relogio);
        if (duracaoSessao == null || duracaoSessao.isNegative() || duracaoSessao.isZero()) {
            throw new IllegalArgumentException("Duração da sessão inválida.");
        }
        this.duracaoSessao = duracaoSessao;
    }

    public synchronized Mergulhador cadastrar(String nome, String email, String senha) {
        String chave = normalizarEmail(email);
        if (usuarios.containsKey(chave)) throw new IllegalArgumentException("E-mail já cadastrado.");
        Mergulhador usuario = new Mergulhador(nome, chave, Senhas.proteger(senha));
        usuarios.put(chave, usuario);
        return usuario;
    }

    public synchronized String login(String email, String senha) {
        return entrar(email, senha).getId().toString();
    }

    public synchronized Sessao entrar(String email, String senha) {
        Mergulhador usuario;
        try {
            usuario = usuarios.get(normalizarEmail(email));
        } catch (IllegalArgumentException e) {
            throw new SecurityException("E-mail ou senha inválidos.");
        }
        if (usuario == null || !usuario.verificarSenha(senha)) {
            throw new SecurityException("E-mail ou senha inválidos.");
        }
        Sessao sessao = new Sessao(usuario, relogio.instant().plus(duracaoSessao));
        sessoes.put(sessao.getId().toString(), sessao);
        return sessao;
    }

    public synchronized Mergulhador usuarioDaSessao(String sessao) {
        Sessao atual = sessoes.get(sessao);
        if (atual == null || !atual.ativa(relogio.instant())) {
            sessoes.remove(sessao);
            throw new SecurityException("Sessão inválida ou expirada. Faça login.");
        }
        return atual.getUsuario();
    }

    public synchronized void logout(String sessao) {
        Sessao atual = sessoes.remove(sessao);
        if (atual != null) atual.encerrar();
    }

    public void sair(Sessao sessao) { if (sessao != null) logout(sessao.getId().toString()); }
    public synchronized void atualizarPerfil(String sessao, String nome, Integer experienciaAnterior) {
        usuarioDaSessao(sessao).atualizarPerfil(nome, experienciaAnterior);
    }

    private static String normalizarEmail(String email) {
        if (email == null) throw new IllegalArgumentException("E-mail inválido.");
        String valor = email.trim().toLowerCase(Locale.ROOT);
        if (!valor.matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        return valor;
    }
}
