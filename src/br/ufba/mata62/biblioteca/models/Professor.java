package br.ufba.mata62.biblioteca.models;

import br.ufba.mata62.biblioteca.rules.IRegraEmprestimo;
import br.ufba.mata62.biblioteca.rules.RegraEmprestimoProfessor;

public class Professor extends Usuario {
    public Professor(String codigo, String nome) {
        super(nome, codigo);
    }
    @Override
    public int getLimiteEmprestimo() {
        return Integer.MAX_VALUE;
    }
    @Override
    public int getTempoEmprestimo() {
        return 8;
    }
    @Override
    public IRegraEmprestimo getRegraEmprestimo() {
        return new RegraEmprestimoProfessor();
    }
}
