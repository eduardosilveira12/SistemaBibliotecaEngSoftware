package br.ufba.mata62.biblioteca.observers;

public interface ISubject {
    void registrarObservador(IObservador observador);
    void removerObservador(IObservador observador);
    void notificarObservadores();
}
