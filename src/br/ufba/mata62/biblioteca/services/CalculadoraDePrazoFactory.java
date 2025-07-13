package br.ufba.mata62.biblioteca.services;

public class CalculadoraDePrazoFactory {
  public static ICalculadoraDePrazo criarCalculadora(String tipo) {
    switch (tipo) {
      case "ALUNO_GRADUACAO":
        return new CalculadoraDePrazoAlunoGraduacao();
      case "ALUNO_POS_GRADUACAO":
        return new CalculadoraDePrazoAlunoPosGraduacao();
      case "PROFESSOR":
        return new CalculadoraDePrazoProfessor();
      default:
        throw new IllegalArgumentException("Tipo de usuário desconhecido: " + tipo);
    }
  }

}
