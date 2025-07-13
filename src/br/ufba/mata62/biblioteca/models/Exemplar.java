package br.ufba.mata62.biblioteca.models;

public class Exemplar {
    private String codigo;
    private Livro livro;
    private StatusExemplar status;

    public Exemplar(String codigo, Livro livro) {
        this.codigo = codigo;
        this.livro = livro;
        this.status = StatusExemplar.DISPONIVEL; // Inicialmente disponível
    }

    public Livro getLivro() {
        return livro;
    }

    public StatusExemplar getStatus() {
        return status;
    }

    public void setStatus(StatusExemplar status) {
        this.status = status;
    }
}
