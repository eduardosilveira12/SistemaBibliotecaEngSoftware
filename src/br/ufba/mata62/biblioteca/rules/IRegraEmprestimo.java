package br.ufba.mata62.biblioteca.rules;

import br.ufba.mata62.biblioteca.models.Livro;
import br.ufba.mata62.biblioteca.models.Usuario;

public interface IRegraEmprestimo{
    void validar(Usuario usuario, Livro livro) throws Exception;
}