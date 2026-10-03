package br.com.deeplog.model;
import java.util.*;
public class Colecao {
    private final UUID id = UUID.randomUUID();
    private final Mergulhador proprietario;
    private String titulo;
    private final Set<RegistroMergulho> registros = new LinkedHashSet<>();
    public Colecao(Mergulhador proprietario, String titulo) {
        this.proprietario = Objects.requireNonNull(proprietario);
        setTitulo(titulo);
    }
    public void adicionar(RegistroMergulho registro) {
        if (registro == null || registro.getMergulhador() != proprietario) throw new IllegalArgumentException("Registro de outro usuário.");
        registros.add(registro);
    }
    public void remover(RegistroMergulho registro) { registros.remove(registro); }
    public UUID getId() { return id; }
    public Mergulhador getProprietario() { return proprietario; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("Título obrigatório.");
        this.titulo = titulo;
    }
    public Set<RegistroMergulho> getRegistros() { return Collections.unmodifiableSet(registros); }
}
