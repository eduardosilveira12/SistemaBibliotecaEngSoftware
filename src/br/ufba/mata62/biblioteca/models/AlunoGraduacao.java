package br.ufba.mata62.biblioteca.models;

import br.ufba.mata62.biblioteca.rules.IRegraEmprestimo;
import br.ufba.mata62.biblioteca.rules.RegraEmprestimoAluno;

public class AlunoGraduacao extends Usuario{
    public AlunoGraduacao(String codigo,String nome){
        super(codigo,nome);
    }
    @Override
    public int getLimiteEmprestimo() {
        return 2;
    }

    @Override
    public int getTempoEmprestimo() {
        return 4; 
    }
    @Override
    public IRegraEmprestimo getRegraEmprestimo() {
        return new RegraEmprestimoAluno();
    }
}
