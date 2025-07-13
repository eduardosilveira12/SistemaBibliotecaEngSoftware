package br.ufba.mata62.biblioteca.models;

import java.time.LocalDate;


public class Reserva {
    private final Usuario usuario;
    private final Livro livro;
    private final LocalDate dataReserva;

    public Reserva(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataReserva = LocalDate.now(); // Garante a regra de negócio
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }
}