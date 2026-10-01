package br.com.deeplog.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Colecao {
    private String titulo;
    private String descricao;
    private final List<Fotografia> fotografias;

    public Colecao(String titulo, String descricao) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título da coleção é obrigatório.");
        }
        this.titulo = titulo;
        this.descricao = descricao;
        this.fotografias = new ArrayList<>();
    }

    public void adicionarFotografia(Fotografia foto) {
        if (foto != null) {
            this.fotografias.add(foto);
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Fotografia> getFotografias() {
        return Collections.unmodifiableList(fotografias);
    }
}
