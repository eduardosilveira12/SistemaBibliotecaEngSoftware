package br.ufba.mata62.biblioteca.models;

import java.time.LocalDate;

public class Reserva {
  private Usuario usuario;
  private Livro livro;
  private LocalDate dataReserva;

  public Reserva(Usuario usuario, Livro livro, LocalDate dataReserva) {
    this.usuario = usuario;
    this.livro = livro;
    this.dataReserva = dataReserva;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Livro getLivro() {
    return livro;
  }

  public void setLivro(Livro livro) {
    this.livro = livro;
  }

  public LocalDate getDataReserva() {
    return dataReserva;
  }

  public void setDataReserva(LocalDate dataReserva) {
    this.dataReserva = dataReserva;
  }
}
