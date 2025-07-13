package br.ufba.mata62.biblioteca.models;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String codigo;
    private String titulo;
    private String editora;
    private List<String> autores;
    private int edicao;
    private int anoPublicacao;

    private List<Exemplar> exemplares;
    private List<Reserva> reservas;

    public Livro(String codigo, String titulo, String editora, List<String> autores, int edicao, int anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
        this.exemplares = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }
    public String getCodigo() {
        return codigo;
    }
    public List<Reserva> getReservas() {
        return this.reservas;
    }
    public void addExemplar(Exemplar exemplar) {
        this.exemplares.add(exemplar);
    }
    public void addReserva(Reserva reserva) {
        this.reservas.add(reserva);
    }
    public int getNumExemplaresDisponiveis() {
        int disponiveis = 0;
        for (Exemplar exemplar : this.exemplares) {
            if (exemplar.getStatus() == StatusExemplar.DISPONIVEL) {
                disponiveis++;
            }
        }
        return disponiveis;
    }
    public boolean usuarioTemReserva(Usuario usuario) {
        for (Reserva reserva : this.reservas) {
            if (reserva.getUsuario().getCodigo().equals(usuario.getCodigo())) {
                return true;
            }
        }
        return false;
    }
}
