package br.com.deeplog.model;

public class Certificacao {
    private String nome;
    private String organizacaoEmissora;
    private String nivel;

    public Certificacao(String nome, String organizacaoEmissora, String nivel) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da certificação é obrigatório.");
        }
        this.nome = nome;
        this.organizacaoEmissora = organizacaoEmissora;
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public String getOrganizacaoEmissora() {
        return organizacaoEmissora;
    }

    public String getNivel() {
        return nivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificacao that = (Certificacao) o;
        return nome.equalsIgnoreCase(that.nome);
    }

    @Override
    public int hashCode() {
        return nome.toLowerCase().hashCode();
    }
}
