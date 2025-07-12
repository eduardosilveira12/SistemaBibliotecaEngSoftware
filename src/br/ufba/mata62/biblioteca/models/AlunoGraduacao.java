package br.ufba.mata62.biblioteca.models;

public class AlunoGraduacao extends Usuario {

  private String curso;
  private int anoIngresso;

  public AlunoGraduacao(int id, String nome, String curso, int anoIngresso) {
    super(id, nome);
    this.curso = curso;
    this.anoIngresso = anoIngresso;
  }

  public String getCurso() {
    return curso;
  }

  public int getAnoIngresso() {
    return anoIngresso;
  }
}
