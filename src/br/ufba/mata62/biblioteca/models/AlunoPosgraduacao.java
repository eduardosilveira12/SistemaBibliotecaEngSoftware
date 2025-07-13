package br.ufba.mata62.biblioteca.models;

import br.ufba.mata62.biblioteca.rules.IRegraEmprestimo;
import br.ufba.mata62.biblioteca.rules.RegraEmprestimoAluno;

public class AlunoPosgraduacao extends Usuario {

    public AlunoPosgraduacao(String codigo, String nome) {
        super(codigo, nome);
    }
    @Override
    public int getLimiteEmprestimo() {
        return 3;
    }

    @Override
    public int getTempoEmprestimo() {
        return 5;
    }

    @Override
    public IRegraEmprestimo getRegraEmprestimo() {
        return new RegraEmprestimoAluno();
    }
}
