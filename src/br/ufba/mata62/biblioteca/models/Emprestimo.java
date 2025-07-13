package br.ufba.mata62.biblioteca.models;

import java.time.LocalDate;

public class Emprestimo {
    private Exemplar exemplar;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoRealizada;
    private StatusEmprestimo status;

    public Emprestimo(Exemplar exemplar, Usuario usuario) {
        this.exemplar = exemplar;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucaoPrevista = this.dataEmprestimo.plusDays(usuario.getTempoEmprestimo());
        this.status = StatusEmprestimo.EM_CURSO;
    }


    public Exemplar getExemplar() {return exemplar;}
    public Usuario getUsuario() {return usuario;}
    public LocalDate getDataEmprestimo() {return dataEmprestimo;}
    public LocalDate getDataDevolucaoRealizada() {return dataDevolucaoRealizada;}
    public LocalDate getDataDevolucaoPrevista() {return dataDevolucaoPrevista;}
    public StatusEmprestimo getStatus() { return status;}

    public void finalizar(LocalDate dataDevolucao){
        this.dataDevolucaoRealizada = dataDevolucao;
        this.status = StatusEmprestimo.FINALIZADO;
        this.exemplar.setEmprestimoCorrente(null);
        this.exemplar.setStatus(StatusExemplar.DISPONIVEL);
    }
}
