package br.ufba.mata62.biblioteca.commands;

import br.ufba.mata62.biblioteca.exceptions.BibliotecaException;
import br.ufba.mata62.biblioteca.models.Emprestimo;
import br.ufba.mata62.biblioteca.models.Usuario;
import br.ufba.mata62.biblioteca.persistence.Repositorio;
import java.time.LocalDate;

public class ComandoDev implements IComando {
    private String codigoUsuario;
    private String codigoLivro;

    public ComandoDev(String codigoUsuario, String codigoLivro) {
        this.codigoUsuario = codigoUsuario;
        this.codigoLivro = codigoLivro;
    }

    @Override
    public void executar() throws BibliotecaException {
        Repositorio repositorio = Repositorio.getInstance();
        Usuario usuario = repositorio.buscarUsuarioPorCodigo(codigoUsuario);

        if (usuario == null) {
            throw new BibliotecaException("Usuário não encontrado.");
        }

        Emprestimo emprestimo = usuario.getEmprestimoCorrente(codigoLivro);

        if (emprestimo != null) {
            emprestimo.finalizar(LocalDate.now());
        } else {
            throw new BibliotecaException("Não foi encontrado um empréstimo em aberto para este livro e usuário.");
        }
    }
}