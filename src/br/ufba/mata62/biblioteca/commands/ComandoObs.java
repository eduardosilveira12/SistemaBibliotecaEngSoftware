package br.ufba.mata62.biblioteca.commands;

import br.ufba.mata62.biblioteca.models.Livro;
import br.ufba.mata62.biblioteca.models.Usuario;
import br.ufba.mata62.biblioteca.observers.IObservador;
import br.ufba.mata62.biblioteca.persistence.Repositorio;

public class ComandoObs implements IComando{
    private String codigoLivro;
    private String codigoUsuario;

    public ComandoObs(String codigoLivro, String codigoUsuario) {
        this.codigoLivro = codigoLivro;
        this.codigoUsuario = codigoUsuario;
    }
    @Override
    public void executar() {
        Repositorio repositorio = Repositorio.getInstance();
        Usuario usuario = repositorio.buscarUsuarioPorCodigo(this.codigoUsuario);
        Livro livro = repositorio.buscarLivroPorCodigo(this.codigoLivro);
        if (livro != null) {
            System.out.println("Livro com código " + this.codigoLivro + " não encontrado.");
            return;
        }
        if (usuario == null) {
            System.out.println("Usuário com código " + this.codigoUsuario + " não encontrado.");
            return;
        }
        if (usuario instanceof IObservador){
            livro.registrarObservador((IObservador)usuario);
            System.out.println("Observacão do livro " + livro.getTitulo() + " por " + usuario.getNome() + " registrada com sucesso.");
        }
    }
}
