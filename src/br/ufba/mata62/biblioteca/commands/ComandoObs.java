package br.ufba.mata62.biblioteca.commands;

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
    public void executar() {
        Repositorio repositorio = Repositorio.getInstance();
        Usuario usuario = repositorio.buscarUsuarioPorCodigo(this.codigoUsuario);
        Livro livro = repositorio.buscarLivroPorCodigo(this.codigoLivro);

        if (usuario == null) {
            System.out.println("Usuário com código " + this.codigoUsuario + " não encontrado.");
            return;
        }

        if (livro == null) {
            System.out.println("Livro com código " + this.codigoLivro + " não encontrado.");
            return;
        }

        if (usuario instanceof IObservador) {
            livro.registrarObservador((IObservador) usuario);
            System.out.println("Observação do livro " + livro.getTitulo() + " por " + usuario.getNome() + " registrada com sucesso.");
        } else {
            System.out.println("Este tipo de usuário não pode observar livros.");
        }
    }
}