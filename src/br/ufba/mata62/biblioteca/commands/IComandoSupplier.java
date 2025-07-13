package br.ufba.mata62.biblioteca.commands;

import br.ufba.mata62.biblioteca.exceptions.BibliotecaException;

@FunctionalInterface
public interface IComandoSupplier {
    IComando create(String[] args) throws BibliotecaException;
}
