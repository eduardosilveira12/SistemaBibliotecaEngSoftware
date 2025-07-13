package br.ufba.mata62.biblioteca.observers;

import br.ufba.mata62.biblioteca.models.Livro;

public interface IObservador {
    void notificar(Livro livro);
}
