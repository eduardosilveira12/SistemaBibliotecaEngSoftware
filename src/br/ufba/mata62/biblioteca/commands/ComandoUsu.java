package br.ufba.mata62.biblioteca.commands;

import br.ufba.mata62.biblioteca.models.Emprestimo;
import br.ufba.mata62.biblioteca.models.Reserva;
import br.ufba.mata62.biblioteca.models.Usuario;
import br.ufba.mata62.biblioteca.persistence.Repositorio;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ComandoUsu implements IComando{
    private String codigoUsuario;
    public ComandoUsu(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
    @Override
    public void executar() {
        Repositorio repositorio = Repositorio.getInstance();
        Usuario usuario = repositorio.buscarUsuarioPorCodigo(codigoUsuario);
        if (usuario == null) {
            System.out.println("Usuario com código " + codigoUsuario + " não encontrado.");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("--- Emprestimos ---");
        List<Emprestimo> emprestimos = usuario.getEmprestimos();
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo encontrado.");
        } else {
            for (Emprestimo e : emprestimos) {
                System.out.println("Título: " + e.getExemplar().getLivro().getTitulo());
                System.out.println("  Data do empréstimo: " + e.getDataEmprestimo().format(formatter));
                System.out.println("  Status: " + e.getStatus());
                if (e.getDataDevolucaoRealizada() != null) {
                    System.out.println("  Data da devolução: " + e.getDataDevolucaoRealizada().format(formatter));
                } else {
                    System.out.println("  Devolução prevista: " + e.getDataDevolucaoPrevista().format(formatter));
                }
                System.out.println();
            }
        }
        System.out.println("--- Reservas ---");
        List<Reserva> reservas = repositorio.buscarReservasPorUsuario(usuario);
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva ativa.");
        } else {
            for (Reserva r : reservas) {
                System.out.println("Título: " + r.getLivro().getTitulo());
                System.out.println("  Data da reserva: " + r.getDataReserva().format(formatter));
                System.out.println();
            }
        }

    }
}
