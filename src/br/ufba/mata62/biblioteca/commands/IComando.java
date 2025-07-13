package br.ufba.mata62.biblioteca.commands;

import br.ufba.mata62.biblioteca.exceptions.BibliotecaException;

public interface IComando {
    void executar() throws BibliotecaException;
}
