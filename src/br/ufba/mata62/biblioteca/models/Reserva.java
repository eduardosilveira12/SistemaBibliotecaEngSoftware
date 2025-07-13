package br.ufba.mata62.biblioteca.models;

import java.time.LocalDate;

public class Reserva {
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataReserva;

    public Reserva(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataReserva = LocalDate.now();
    }
    public Usuario getUsuario() {return usuario;}
}
