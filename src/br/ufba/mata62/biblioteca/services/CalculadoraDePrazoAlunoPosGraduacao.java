package br.ufba.mata62.biblioteca.services;

public class CalculadoraDePrazoAlunoPosGraduacao implements ICalculadoraDePrazo {
  @Override
  public int diasDeEmprestimo() {
    return 5;
  }

}
