package br.ufba.mata62.biblioteca.models;

public class Exemplar {
    private String codigo;
    private Livro livro;
    private StatusExemplar status;
    private Emprestimo emprestimoCorrente;

    public Exemplar(String codigo, Livro livro) {
        this.codigo = codigo;
        this.livro = livro;
        this.status = StatusExemplar.DISPONIVEL;
        this.emprestimoCorrente = null;
    }

    public String getCodigo() {
        return codigo;
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

    public Emprestimo getEmprestimoCorrente() {
        return emprestimoCorrente;
    }

    public void setEmprestimoCorrente(Emprestimo emprestimo) {
        this.emprestimoCorrente = emprestimo;
    }
}