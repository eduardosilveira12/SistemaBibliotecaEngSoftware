package br.ufba.mata62.biblioteca.commands;

import br.ufba.mata62.biblioteca.models.Emprestimo;
import br.ufba.mata62.biblioteca.models.Usuario;
import br.ufba.mata62.biblioteca.persistence.Repositorio;

import java.time.LocalDate;

public class ComandoDev implements IComando {
    private  String codigoUsuario;
    private  String codigoLivro;

    public ComandoDev(String codigoUsuario, String codigoLivro) {
        this.codigoUsuario = codigoUsuario;
        this.codigoLivro = codigoLivro;
    }

    @Override
    public void executar() {
        Repositorio repositorio = Repositorio.getInstance();
        Usuario usuario = repositorio.buscarUsuarioPorCodigo(codigoUsuario);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        Emprestimo emprestimo = usuario.getEmprestimoCorrente(codigoLivro);

        if (emprestimo != null) {
            emprestimo.finalizar(LocalDate.now());
            System.out.println("Devolução do livro '" + emprestimo.getExemplar().getLivro().getTitulo() + "' realizada com sucesso.");
        } else {
            System.out.println("Não foi encontrado um empréstimo em aberto para este livro e usuário.");
        }
    }
}
