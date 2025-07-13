package br.ufba.mata62.biblioteca.rules;

import br.ufba.mata62.biblioteca.models.Livro;
import br.ufba.mata62.biblioteca.models.Usuario;

public class RegraEmprestimoProfessor implements  IRegraEmprestimo {
    @Override
    public void validar(Usuario usuario, Livro livro) throws Exception {
        // Violação clara do principio DRY,TODO: implementar design pattern referente a lógica de verificação
        // Verifica se há exemplos disponíveis
        if (livro.getNumExemplaresDisponiveis() == 0) {
            throw new Exception("Não há exemplares deste livro disponíveis no momento.");
        }
        // Verifica se usuário está devedor
        if (usuario.isDevedor()){
            throw new Exception("Não foi possível realizar o empréstimo, pois o usuário possui livros em atraso.");
        }
    }
}
