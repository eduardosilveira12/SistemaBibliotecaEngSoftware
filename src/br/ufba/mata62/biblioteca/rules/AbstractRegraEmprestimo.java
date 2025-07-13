package br.ufba.mata62.biblioteca.rules;

import br.ufba.mata62.biblioteca.models.Livro;
import br.ufba.mata62.biblioteca.models.Usuario;

public abstract class AbstractRegraEmprestimo implements IRegraEmprestimo {
    @Override
    public final void validar(Usuario usuario, Livro livro) throws Exception {
        verificarDebitoUsuario(usuario);
        verificarDisponibilidade(livro);
        verificarRegrasEspecificas(usuario, livro);
    }
    private void verificarDebitoUsuario(Usuario usuario) throws Exception {
        if (usuario.isDevedor()) {
            throw new Exception("Não foi possível realizar o empréstimo, pois o usuário possui livros em atraso.");
        }
    }
    private void verificarDisponibilidade(Livro livro) throws Exception {
        if (livro.getNumExemplaresDisponiveis() == 0) {
            throw new Exception("Não há exemplares deste livro disponíveis no momento.");
        }
    }
    protected abstract void verificarRegrasEspecificas(Usuario usuario, Livro livro) throws Exception;
}

