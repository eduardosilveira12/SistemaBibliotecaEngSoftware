package br.ufba.mata62.biblioteca.models;

import java.time.LocalDate;

public class Emprestimo {
    private Exemplar exemplar;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoRealizada;
    private StatusEmprestimo status;

    public Emprestimo(Exemplar exemplar, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.exemplar = exemplar;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucaoPrevista = this.dataEmprestimo.plusDays(Usuario.getTempoEmprestimo());
        this.status = StatusEmprestimo.EM_CURSO;
    }

    public Exemplar getExemplar() {return exemplar;}
    public LocalDate getDataDevolucaoPrevista() {return dataDevolucaoPrevista;}

    public StatusEmprestimo getStatus() { return status;}
    public void finalizar(LocalDate dataDevolucao){
        this.dataDevolucaoRealizada = dataDevolucao;
        this.status = StatusEmprestimo.FINALIZADO;
    }
}
