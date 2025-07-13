package br.ufba.mata62.biblioteca.models;

import br.ufba.mata62.biblioteca.observers.IObservador;
import br.ufba.mata62.biblioteca.rules.IRegraEmprestimo;
import br.ufba.mata62.biblioteca.rules.RegraEmprestimoProfessor;

public class Professor extends Usuario implements IObservador {
    private int contadorNotificacoes;

    public Professor(String codigo, String nome) {
        super(codigo, nome);
        this.contadorNotificacoes = 0;
    }
    @Override
    public void notificar(Livro livro) {
        this.contadorNotificacoes++;
        System.out.println(
                "Professor " + getNome() +
                        " notificado. Total de notificações: " + this.contadorNotificacoes +
                        ". Causa: Livro '" + livro.getCodigo() + "' excedeu 2 reservas."
        );
    }
    public int getContadorNotificacoes() {
        return contadorNotificacoes;
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
