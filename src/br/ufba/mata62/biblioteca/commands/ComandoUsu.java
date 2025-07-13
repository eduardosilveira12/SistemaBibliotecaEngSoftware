package br.ufba.mata62.biblioteca.commands;

import br.ufba.mata62.biblioteca.exceptions.BibliotecaException;
import br.ufba.mata62.biblioteca.models.*;
import br.ufba.mata62.biblioteca.persistence.Repositorio;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ComandoUsu implements IComando {
    private String codigoUsuario;

    public ComandoUsu(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    @Override
    public void executar() throws BibliotecaException {
        Repositorio repositorio = Repositorio.getInstance();
        Usuario usuario = repositorio.buscarUsuarioPorCodigo(codigoUsuario);

        if (usuario == null) {
            throw new BibliotecaException("Usuário não encontrado.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("--- Histórico do Usuário ---");
        System.out.println("Nome: " + usuario.getNome());

        System.out.println("\n--- Empréstimos ---");
        List<Emprestimo> emprestimos = usuario.getEmprestimos();
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
        } else {
            for (Emprestimo e : emprestimos) {
                System.out.println("Livro: " + e.getExemplar().getLivro().getTitulo());
                System.out.println("  > Data: " + e.getDataEmprestimo().format(formatter));
                System.out.println("  > Status: " + e.getStatus());
                if (e.getStatus() == StatusEmprestimo.FINALIZADO) {
                    System.out.println("  > Devolvido em: " + e.getDataDevolucaoRealizada().format(formatter));
                } else {
                    System.out.println("  > Devolução Prevista: " + e.getDataDevolucaoPrevista().format(formatter));
                }
            }
        }

        System.out.println("\n--- Reservas Ativas ---");
        List<Reserva> reservas = repositorio.buscarReservasPorUsuario(usuario);
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva ativa.");
        } else {
            for (Reserva r : reservas) {
                System.out.println("Livro: " + r.getLivro().getTitulo());
                System.out.println("  > Data da Reserva: " + r.getDataReserva().format(formatter));
            }
        }
        System.out.println("--------------------------");
    }
}