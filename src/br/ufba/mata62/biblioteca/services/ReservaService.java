package br.ufba.mata62.biblioteca.services;

import br.ufba.mata62.biblioteca.models.*;
import br.ufba.mata62.biblioteca.repositories.Repositorio;
import br.ufba.mata62.biblioteca.repositories.ReservasRepositorio;

import java.time.LocalDate;

public class ReservaService {
  public static void reservarLivro(int idUsuario, int idLivro) {
    Repositorio repositorio = Repositorio.getInstance();
    ReservasRepositorio reservasRepositorio = ReservasRepositorio.getInstance();

    Usuario usuario = repositorio.buscarUsuarioPorId(idUsuario);
    Livro livro = repositorio.buscarLivroPorId(idLivro);

    if (usuario == null || livro == null) {
      System.out.println("Usuário ou livro não encontrado.");
      return;
    }

    Reserva reserva = new Reserva(usuario, livro, LocalDate.now());
    reservasRepositorio.salvar(reserva);
    System.out.println("Reserva realizada com sucesso!");
  }
}
