package br.ufba.mata62.biblioteca.commands;

import br.ufba.mata62.biblioteca.exceptions.BibliotecaException;
import br.ufba.mata62.biblioteca.models.*;
import br.ufba.mata62.biblioteca.persistence.Repositorio;
import java.time.format.DateTimeFormatter;

public class ComandoLiv implements IComando {
    private String codigoLivro;

    public ComandoLiv(String codigoLivro) {
        this.codigoLivro = codigoLivro;
    }

    @Override
    public void executar() throws BibliotecaException {
        Repositorio repositorio = Repositorio.getInstance();
        Livro livro = repositorio.buscarLivroPorCodigo(codigoLivro);

        if (livro == null) {
            throw new BibliotecaException("Livro com código " + codigoLivro + " não encontrado.");
        }

        System.out.println("--- Detalhes do Livro ---");
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Reservas: " + livro.getReservas().size());
        if (!livro.getReservas().isEmpty()) {
            System.out.println("Fila de reserva:");
            for (Reserva r : livro.getReservas()) {
                System.out.println("- " + r.getUsuario().getNome());
            }
        }
        System.out.println("\n--- Exemplares ---");
        for (Exemplar exemplar : livro.getExemplares()) {
            System.out.println("Cód. Exemplar: " + exemplar.getCodigo() + " | Status: " + exemplar.getStatus());
            if (exemplar.getStatus() == StatusExemplar.EMPRESTADO) {
                Emprestimo emprestimo = exemplar.getEmprestimoCorrente();
                if (emprestimo != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    System.out.println("  > Emprestado para: " + emprestimo.getUsuario().getNome());
                    System.out.println("  > Data Empréstimo: " + emprestimo.getDataEmprestimo().format(formatter));
                    System.out.println("  > Devolução Prevista: " + emprestimo.getDataDevolucaoPrevista().format(formatter));
                }
            }
        }
        System.out.println("-------------------------");
    }
}