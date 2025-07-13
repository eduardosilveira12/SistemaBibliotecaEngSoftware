package br.ufba.mata62.biblioteca.models;

import br.ufba.mata62.biblioteca.rules.IRegraEmprestimo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Usuario {
    private String nome;
    private String codigo;
    private List<Emprestimo> emprestimos;

    public Usuario(String codigo, String nome) {
        this.nome = nome;
        this.codigo = codigo;
        this.emprestimos = new ArrayList<>();
    }
    public String getNome() {
        return nome;
    }
    public String getCodigo() {
        return codigo;
    }

    // Métodos Abstratos a serem implementados dentre de suas subclasses
    public abstract int getLimiteEmprestimo();
    public abstract int getTempoEmprestimo();
    public abstract IRegraEmprestimo getRegraEmprestimo();
    // Lógica de Verificação de  Empréstimos

    public List<Emprestimo> getEmprestimosCorrentes() {
        return this.emprestimos.stream().filter(e -> e.getStatus() == StatusEmprestimo.EM_CURSO).collect(Collectors.toList());
    }
    public boolean isDevedor() {
        for (Emprestimo emprestimo : getEmprestimosCorrentes()) {
            if (LocalDate.now().isAfter(emprestimo.getDataDevolucaoPrevista())){
                return true;
            }
        }
        return false;
    }
    public boolean temEmprestimoDoLivro(String codigoLivro){
        for (Emprestimo emprestimo : getEmprestimosCorrentes()){
            if(emprestimo.getExemplar().getLivro().getCodigo().equals(codigoLivro)){
                return true;
            }
        }
        return false;
    }
    public void addEmprestimo(Emprestimo emprestimo){
        this.emprestimos.add(emprestimo);
    }

}
