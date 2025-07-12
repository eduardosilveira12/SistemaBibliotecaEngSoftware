package br.ufba.mata62.biblioteca.persistence;

import br.ufba.mata62.biblioteca.models.Livro;
import br.ufba.mata62.biblioteca.models.Usuario;

import java.util.ArrayList;
import java.util.List;

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
        //TODO
    }
    public Usuario buscarUsuarioPorCodigo(String codigo) {
        for (Usuario usuario : this.usuarios) {
            if (usuario.getCodigo().equals(codigo)){
                return usuario;
            }
        }
        return null;
    }
    public Livro buscarLivroPorCodigo(String codigo) {
        for (Livro livro : this.livros) {
            if (livro.getCodigo().equals(codigo)){
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
}