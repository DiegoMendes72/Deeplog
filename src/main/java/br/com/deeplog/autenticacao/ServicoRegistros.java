package br.com.deeplog.autenticacao;

import br.com.deeplog.model.*;
import java.time.LocalDateTime;
import java.util.*;

public class ServicoRegistros {
    private final ServicoAutenticacao autenticacao;
    private final Map<UUID, RegistroMergulho> registros = new LinkedHashMap<>();

    public ServicoRegistros(ServicoAutenticacao autenticacao) {
        this.autenticacao = Objects.requireNonNull(autenticacao);
    }

    public synchronized UUID registrar(String sessao, PerfilMergulho perfil,
            LocalDateTime data, int duracao, double profundidade) {
        Mergulhador dono = autenticacao.usuarioDaSessao(sessao);
        RegistroMergulho registro = new RegistroMergulho(dono, perfil, data, duracao, profundidade);
        UUID id = UUID.randomUUID();
        registros.put(id, registro);
        dono.incrementarMergulhos();
        return id;
    }

    public synchronized RegistroMergulho consultar(String sessao, UUID id) {
        Mergulhador dono = autenticacao.usuarioDaSessao(sessao);
        RegistroMergulho registro = registros.get(id);
        if (registro == null || registro.getAutor() != dono) {
            throw new SecurityException("Registro indisponível para este usuário.");
        }
        return registro;
    }

    public synchronized List<RegistroMergulho> listar(String sessao) {
        Mergulhador dono = autenticacao.usuarioDaSessao(sessao);
        List<RegistroMergulho> resultado = new ArrayList<>();
        for (RegistroMergulho registro : registros.values()) {
            if (registro.getAutor() == dono) resultado.add(registro);
        }
        return Collections.unmodifiableList(resultado);
    }
}
