package br.com.deeplog.autenticacao;

import br.com.deeplog.model.Mergulhador;
import br.com.deeplog.excecoes.AutenticacaoException;
import br.com.deeplog.excecoes.ValidacaoException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Cadastro, login e logout dos mergulhadores.
 */
public class ServicoAutenticacao {
    private final Map<String, Mergulhador> usuarios = new HashMap<>();
    private final Map<String, Sessao> sessoes = new HashMap<>();
    private final java.time.Clock relogio;
    private final java.time.Duration duracaoSessao;

    public ServicoAutenticacao() { this(java.time.Clock.systemUTC(), java.time.Duration.ofHours(8)); }
    public ServicoAutenticacao(java.time.Clock relogio, java.time.Duration duracaoSessao) {
        this.relogio = java.util.Objects.requireNonNull(relogio);
        if (duracaoSessao == null || duracaoSessao.isNegative() || duracaoSessao.isZero()) {
            throw new ValidacaoException("Duração da sessão inválida.");
        }
        this.duracaoSessao = duracaoSessao;
    }

    /** Cadastra um novo mergulhador. */
    public synchronized Mergulhador cadastrar(String nome, String email, String senha) {
        String chave = normalizarEmail(email);
        if (usuarios.containsKey(chave)) throw new ValidacaoException("E-mail já cadastrado.");
        Mergulhador usuario = new Mergulhador(nome, chave, Senhas.proteger(senha));
        usuarios.put(chave, usuario);
        return usuario;
    }

    /** Faz login e devolve o código da sessão. */
    public synchronized String login(String email, String senha) {
        return entrar(email, senha).getId().toString();
    }

    /** Faz login e devolve a sessão. */
    public synchronized Sessao entrar(String email, String senha) {
        Mergulhador usuario;
        try {
            usuario = usuarios.get(normalizarEmail(email));
        } catch (ValidacaoException e) {
            throw new AutenticacaoException("E-mail ou senha inválidos.");
        }
        if (usuario == null || !usuario.verificarSenha(senha)) {
            throw new AutenticacaoException("E-mail ou senha inválidos.");
        }
        Sessao sessao = new Sessao(usuario, relogio.instant().plus(duracaoSessao));
        sessoes.put(sessao.getId().toString(), sessao);
        return sessao;
    }

    /** Devolve o usuário de uma sessão ativa. */
    public synchronized Mergulhador usuarioDaSessao(String sessao) {
        Sessao atual = sessoes.get(sessao);
        if (atual == null || !atual.ativa(relogio.instant())) {
            sessoes.remove(sessao);
            throw new AutenticacaoException("Sessão inválida ou expirada. Faça login.");
        }
        return atual.getUsuario();
    }

    /** Encerra a sessão. */
    public synchronized void logout(String token) {
        if (token != null) {
            sessoes.remove(token);
        }
    }

    /** Encerra a sessão informada. */
    public void sair(Sessao sessao) { if (sessao != null) logout(sessao.getId().toString()); }
    
    /** Atualiza o perfil do usuário logado. */
    public synchronized void atualizarPerfil(String sessao, String nome, Integer experienciaAnterior) {
        usuarioDaSessao(sessao).atualizarPerfil(nome, experienciaAnterior);
    }

    private static String normalizarEmail(String email) {
        if (email == null) throw new ValidacaoException("E-mail inválido.");
        String valor = email.trim().toLowerCase(Locale.ROOT);
        if (!valor.matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+")) {
            throw new ValidacaoException("E-mail inválido.");
        }
        return valor;
    }
}
