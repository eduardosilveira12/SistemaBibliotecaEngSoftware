package br.ufba.mata62.biblioteca.models;

import java.util.List;

public class Livro {

  private int id;
  private String titulo;
  private String editora;
  private List<String> autores;
  private int edicao;
  private int anoPublicacao;

  public Livro(int id, String titulo, String editora, List<String> autores) {
    this.id = id;
    this.titulo = titulo;
    this.editora = editora;
    this.autores = autores;
  }

}
