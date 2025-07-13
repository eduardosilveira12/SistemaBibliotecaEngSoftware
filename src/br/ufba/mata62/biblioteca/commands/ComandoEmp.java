package br.ufba.mata62.biblioteca.commands;

import br.ufba.mata62.biblioteca.exceptions.BibliotecaException;
import br.ufba.mata62.biblioteca.models.*;
import br.ufba.mata62.biblioteca.persistence.Repositorio;
import br.ufba.mata62.biblioteca.rules.IRegraEmprestimo;

public class ComandoEmp implements IComando{
    private String codigoUsuario;
    private String codigoLivro;

    public ComandoEmp(String codigoUsuario, String codigoLivro) {
        this.codigoUsuario = codigoUsuario;
        this.codigoLivro = codigoLivro;
    }

    @Override
    public void executar() throws BibliotecaException {
        Repositorio repositorio = Repositorio.getInstance();
        Usuario usuario = repositorio.buscarUsuarioPorCodigo(this.codigoUsuario);
        Livro livro = repositorio.buscarLivroPorCodigo(this.codigoLivro);
        if (usuario == null || livro == null) {
            throw new BibliotecaException("Usuário ou livro não encontrado.");
        }

        try {
            IRegraEmprestimo regra = usuario.getRegraEmprestimo();
            regra.validar(usuario, livro);

            Exemplar exemplarDisponivel = livro.getExemplarDisponivel();
            if (exemplarDisponivel == null) {
                throw new BibliotecaException("Não há exemplares deste livro disponíveis no momento.");
            }

            livro.removerReserva(usuario);
            Emprestimo emprestimo = new Emprestimo(exemplarDisponivel, usuario);
            usuario.addEmprestimo(emprestimo);
            exemplarDisponivel.setStatus(StatusExemplar.EMPRESTADO);
            exemplarDisponivel.setEmprestimoCorrente(emprestimo);

        } catch (Exception e) {
            throw new BibliotecaException(e.getMessage());
        }
    }
}

