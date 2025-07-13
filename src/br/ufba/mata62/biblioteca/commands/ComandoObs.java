package br.ufba.mata62.biblioteca.commands;

import br.ufba.mata62.biblioteca.exceptions.BibliotecaException;
import br.ufba.mata62.biblioteca.models.Livro;
import br.ufba.mata62.biblioteca.models.Usuario;
import br.ufba.mata62.biblioteca.observers.IObservador;
import br.ufba.mata62.biblioteca.persistence.Repositorio;

public class ComandoObs implements IComando {
    private String codigoUsuario;
    private String codigoLivro;

    public ComandoObs(String codigoUsuario, String codigoLivro) {
        this.codigoUsuario = codigoUsuario;
        this.codigoLivro = codigoLivro;
    }

    @Override
    public void executar() throws BibliotecaException {
        Repositorio repositorio = Repositorio.getInstance();
        Usuario usuario = repositorio.buscarUsuarioPorCodigo(this.codigoUsuario);
        Livro livro = repositorio.buscarLivroPorCodigo(this.codigoLivro);

        if (usuario == null) {
            throw new BibliotecaException("Usuário com código " + this.codigoUsuario + " não encontrado.");
        }

        if (livro == null) {
            throw new BibliotecaException("Livro com código " + this.codigoLivro + " não encontrado.");}

        if (usuario instanceof IObservador) {
            livro.registrarObservador((IObservador) usuario);
        } else {
            throw new BibliotecaException("Este tipo de usuário não pode observar livros.");

        }
    }
}