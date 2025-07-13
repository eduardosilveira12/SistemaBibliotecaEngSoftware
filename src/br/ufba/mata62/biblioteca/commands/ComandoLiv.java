package br.ufba.mata62.biblioteca.commands;

import br.ufba.mata62.biblioteca.models.*;
import br.ufba.mata62.biblioteca.persistence.Repositorio;

import java.time.format.DateTimeFormatter;

public class ComandoLiv implements IComando {
    private String codigoLivro;
    public ComandoLiv(String codigoLivro) {
        this.codigoLivro = codigoLivro;
    }
    @Override
    public void executar() {
        Repositorio repositorio = Repositorio.getInstance();
        Livro livro = repositorio.buscarLivroPorCodigo(codigoLivro);
        if (livro == null) {
            System.out.println("Livro com código " + codigoLivro + " não encontrado.");
            return;
        }
        System.out.println("Detalhes do Livro:");
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Quantidade de reservas: " + livro.getReservas().size());
        if (livro.getReservas().size() > 0) {
            System.out.println("Reservado por:");
            for (Reserva reserva : livro.getReservas()) {
                System.out.println("- " + reserva.getUsuario().getNome());
            }
        }

        System.out.println("--- Exemplares ---");
        for (Exemplar exemplar : livro.getExemplares()) {
            System.out.println("Código do exemplar: " + exemplar.getCodigo());
            System.out.println("Status: " + exemplar.getStatus());

            if (exemplar.getStatus() == StatusExemplar.EMPRESTADO) {
                Emprestimo emprestimo = exemplar.getEmprestimoCorrente();
                if (emprestimo != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    System.out.println("  Emprestado para: " + emprestimo.getUsuario().getNome());
                    System.out.println("  Data do empréstimo: " + emprestimo.getDataEmprestimo().format(formatter));
                    System.out.println("  Data de devolução prevista: " + emprestimo.getDataDevolucaoPrevista().format(formatter));
                }
            }
            System.out.println();
        }
    }
}
