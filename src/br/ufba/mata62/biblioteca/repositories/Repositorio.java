package br.ufba.mata62.biblioteca.repositories;

import java.util.ArrayList;
import java.util.List;

import br.ufba.mata62.biblioteca.models.Livro;
import br.ufba.mata62.biblioteca.models.Usuario;

public class Repositorio {
    // estática e privada: padrão Singleton
    private static Repositorio instance;

    private List<Usuario> usuarios;
    private List<Livro> livros;

    private Repositorio() {
        this.usuarios = new ArrayList<>();
        this.livros = new ArrayList<>();
        carregarDadosIniciais();
    }

    public static Repositorio getInstance() {
        if (instance == null) {
            instance = new Repositorio();
        }
        return instance;
    }

    private void carregarDadosIniciais() {
        // TODO
    }

    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuario : this.usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public Livro buscarLivroPorId(int id) {
        for (Livro livro : this.livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }
}