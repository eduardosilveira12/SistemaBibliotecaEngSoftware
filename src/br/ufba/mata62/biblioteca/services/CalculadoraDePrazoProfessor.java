package br.ufba.mata62.biblioteca.services;

public class CalculadoraDePrazoProfessor implements ICalculadoraDePrazo {
  @Override
  public int diasDeEmprestimo() {
    return 8;
  }

}
