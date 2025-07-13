package br.ufba.mata62.biblioteca.rules;

import br.ufba.mata62.biblioteca.models.Livro;
import br.ufba.mata62.biblioteca.models.Usuario;

public class RegraEmprestimoAluno extends AbstractRegraEmprestimo {

    @Override
    protected void verificarRegrasEspecificas(Usuario usuario, Livro livro) throws Exception {
        // 1. Regra Específica: Aluno não pode pegar o mesmo livro duas vezes.
        if (usuario.temEmprestimoDoLivro(livro.getCodigo())) {
            throw new Exception("Não foi possível realizar o empréstimo, pois o usuário já tem um exemplar deste mesmo livro em empréstimo no momento.");
        }

        // 2. Regra Específica: Aluno tem limite de empréstimos.
        if (usuario.getEmprestimosCorrentes().size() >= usuario.getLimiteEmprestimo()) {
            throw new Exception("Não foi possível realizar o empréstimo, pois o usuário atingiu o seu limite de " + usuario.getLimiteEmprestimo() + " empréstimos.");
        }

        // 3. Regra Específica: Aluno deve respeitar a fila de reservas.
        int exemplaresDisponiveis = livro.getNumExemplaresDisponiveis();
        int reservas = livro.getReservas().size();
        if (reservas >= exemplaresDisponiveis && !livro.usuarioTemReserva(usuario)) {
            throw new Exception("Empréstimo não permitido, todos os exemplares disponíveis estão na fila de reserva.");
        }
    }
}