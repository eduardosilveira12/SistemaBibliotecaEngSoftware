package br.ufba.mata62.biblioteca.services;

public class CalculadoraDePrazoAlunoGraduacao implements ICalculadoraDePrazo {
  @Override
  public int diasDeEmprestimo() {
    return 4;
  }

}