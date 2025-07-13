package br.ufba.mata62.biblioteca.commands;

import br.ufba.mata62.biblioteca.exceptions.BibliotecaException;
import br.ufba.mata62.biblioteca.models.Livro;
import br.ufba.mata62.biblioteca.models.Reserva;
import br.ufba.mata62.biblioteca.models.Usuario;
import br.ufba.mata62.biblioteca.persistence.Repositorio;

public class ComandoRes implements IComando {
    private String codigoUsuario;
    private String codigoLivro;

    public ComandoRes(String codigoUsuario, String codigoLivro) {
        this.codigoUsuario = codigoUsuario;
        this.codigoLivro = codigoLivro;
    }

    @Override
    public void executar() throws BibliotecaException {
        Repositorio repositorio = Repositorio.getInstance();
        Usuario usuario = repositorio.buscarUsuarioPorCodigo(codigoUsuario);
        Livro livro = repositorio.buscarLivroPorCodigo(codigoLivro);

        if (usuario == null || livro == null) {
            throw new BibliotecaException("Usuário ou Livro não encontrado.");
        }

        if (livro.usuarioTemReserva(usuario) || usuario.temEmprestimoDoLivro(livro.getCodigo())) {
            throw new BibliotecaException("Não foi possível realizar a reserva. Usuário já possui reserva ou empréstimo ativo para este livro.");
        }

        Reserva novaReserva = new Reserva(usuario, livro);
        livro.addReserva(novaReserva);
    }
}