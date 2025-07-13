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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getEditora() {
    return editora;
  }

  public void setEditora(String editora) {
    this.editora = editora;
  }

  public List<String> getAutores() {
    return autores;
  }

  public void setAutores(List<String> autores) {
    this.autores = autores;
  }

  public int getEdicao() {
    return edicao;
  }

  public void setEdicao(int edicao) {
    this.edicao = edicao;
  }

  public int getAnoPublicacao() {
    return anoPublicacao;
  }

  public void setAnoPublicacao(int anoPublicacao) {
    this.anoPublicacao = anoPublicacao;
  }

}
