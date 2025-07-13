package br.ufba.mata62.biblioteca.models;
import br.ufba.mata62.biblioteca.observers.ISubject;
import br.ufba.mata62.biblioteca.observers.IObservador;
import java.util.ArrayList;
import java.util.List;

public class Livro implements ISubject {
    private String codigo;
    private String titulo;
    private String editora;
    private List<String> autores;
    private int edicao;
    private int anoPublicacao;

    private List<Exemplar> exemplares;
    private List<Reserva> reservas;

    private List<IObservador> observadores;

    public Livro(String codigo, String titulo, String editora, List<String> autores, int edicao, int anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
        this.exemplares = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.observadores = new ArrayList<>();
    }
    public String getCodigo() {
        return codigo;
    }
    public List<Reserva> getReservas() {
        return this.reservas;
    }
    public List<Exemplar> getExemplares() {
        return this.exemplares;
    }
    public void addExemplar(Exemplar exemplar) {
        this.exemplares.add(exemplar);
    }
    public void addReserva(Reserva reserva) {
        this.reservas.add(reserva);
        if (this.reservas.size() > 2) {
            notificarObservadores();
        }
    }
    @Override
    public void registrarObservador(IObservador observador) {
        if (!this.observadores.contains(observador)) {
              this.observadores.add(observador);
        }
    }
    @Override
    public void removerObservador(IObservador observador) {
        this.observadores.remove(observador);
    }
    @Override
    public void notificarObservadores() {
        for (IObservador observador : this.observadores) {
            observador.notificar(this);
        }
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
    public Exemplar getExemplarDisponivel() {
        for (Exemplar exemplar : this.exemplares) {
            if (exemplar.getStatus() == StatusExemplar.DISPONIVEL) {
                return exemplar;
            }
        }
        return null;
    }
    public void removerReserva(Usuario usuario) {
        this.reservas.removeIf(reserva -> reserva.getUsuario().getCodigo().equals(usuario.getCodigo()));
    }
    public String getTitulo() {
        return this.titulo;
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
