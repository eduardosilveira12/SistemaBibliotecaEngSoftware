package br.ufba.mata62.biblioteca.rules;

import br.ufba.mata62.biblioteca.models.Livro;
import br.ufba.mata62.biblioteca.models.Usuario;

public class RegraEmprestimoProfessor extends AbstractRegraEmprestimo {

    @Override
    protected void verificarRegrasEspecificas(Usuario usuario, Livro livro) throws Exception {
        // Professores não possuem regras de empréstimo adicionais.
    }
}