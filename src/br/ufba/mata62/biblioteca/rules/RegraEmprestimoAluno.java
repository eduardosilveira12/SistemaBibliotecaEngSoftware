package br.ufba.mata62.biblioteca.rules;

import br.ufba.mata62.biblioteca.models.Livro;
import br.ufba.mata62.biblioteca.models.Usuario;

public class RegraEmprestimoAluno implements IRegraEmprestimo{
    @Override
    public void validar(Usuario usuario, Livro livro) throws Exception{
        // Violação clara do principio DRY,TODO: implementar design pattern referente a lógica de verificação
        // Verificar se usuário já tem um empréstimo desse livro
        if (usuario.temEmprestimoDoLivro(livro.getCodigo())) {
            throw new Exception("Não foi possível realizar o empréstimo, pois o usuário já tem um exemplar deste mesmo livro em empréstimo no momento.");
        }
        // Verificar se usuário é devedor
        if (usuario.isDevedor()){
            throw new Exception("Não foi possível realizar o empréstimo, pois o usuário possui livros em atraso.");
        }
        // Verifica limite de empréstimos
        if (usuario.getEmprestimosCorrentes().size() >= usuario.getLimiteEmprestimo()){
            throw new Exception("Não foi possível realizar o empréstimo, pois o usuário atingiu o seu limite de " + usuario.getLimiteEmprestimos() + " empréstimos.");
        }
        // Verifica disponibilidade e reservas
        int exemplaresDisponiveis = livro.getNumExemplaresDisponiveis();
        int reservas = livro.getReservas().size();

        if (exemplaresDisponiveis == 0){
            throw new Exception("Não há exemplares deste livro disponíveis no momento.");
        }

        if (reservas >= exemplaresDisponiveis && !livro.usuarioTemReserva(usuario)) {
            throw new Exception("Empréstimo não permitido, todos os exemplares disponíveis estão na fila de reserva.");
        }

    }
}
