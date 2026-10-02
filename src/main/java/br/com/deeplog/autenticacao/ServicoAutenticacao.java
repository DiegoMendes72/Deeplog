package br.com.deeplog.autenticacao;

import br.com.deeplog.model.Mergulhador;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/** Cadastro e sessões em memória; cada login possui sua própria sessão. */
public class ServicoAutenticacao {
    private final Map<String, Mergulhador> usuarios = new HashMap<>();
    private final Map<String, Mergulhador> sessoes = new HashMap<>();

    public synchronized Mergulhador cadastrar(String nome, String email, String senha) {
        String chave = normalizarEmail(email);
        if (usuarios.containsKey(chave)) throw new IllegalArgumentException("E-mail já cadastrado.");
        Mergulhador usuario = new Mergulhador(nome, chave, Senhas.proteger(senha));
        usuarios.put(chave, usuario);
        return usuario;
    }

    public synchronized String login(String email, String senha) {
        Mergulhador usuario;
        try {
            usuario = usuarios.get(normalizarEmail(email));
        } catch (IllegalArgumentException e) {
            throw new SecurityException("E-mail ou senha inválidos.");
        }
        if (usuario == null || !usuario.verificarSenha(senha)) {
            throw new SecurityException("E-mail ou senha inválidos.");
        }
        String sessao = UUID.randomUUID().toString();
        sessoes.put(sessao, usuario);
        return sessao;
    }

    public synchronized Mergulhador usuarioDaSessao(String sessao) {
        Mergulhador usuario = sessoes.get(sessao);
        if (usuario == null) throw new SecurityException("Sessão inválida. Faça login.");
        return usuario;
    }

    public synchronized void logout(String sessao) {
        sessoes.remove(sessao);
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
