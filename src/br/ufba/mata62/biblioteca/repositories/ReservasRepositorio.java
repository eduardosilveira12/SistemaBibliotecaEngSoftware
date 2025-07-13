package br.ufba.mata62.biblioteca.repositories;

import java.util.ArrayList;
import java.util.List;

import br.ufba.mata62.biblioteca.models.Reserva;

public class ReservasRepositorio {
  private static ReservasRepositorio instance;

  private final List<Reserva> reservas;

  private ReservasRepositorio() {
    this.reservas = new ArrayList<>();
    carregarDadosIniciais();
  }

  public static ReservasRepositorio getInstance() {
    if (instance == null) {
      instance = new ReservasRepositorio();
    }
    return instance;
  }

  private void carregarDadosIniciais() {
    // TODO
  }

  public void salvar(Reserva reserva) {
    this.reservas.add(reserva);
  }

  public List<Reserva> listar() {
    return this.reservas;
  }

}
