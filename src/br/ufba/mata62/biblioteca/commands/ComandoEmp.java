package br.ufba.mata62.biblioteca.commands;

import br.ufba.mata62.biblioteca.models.*;
import br.ufba.mata62.biblioteca.persistence.Repositorio;
import br.ufba.mata62.biblioteca.rules.IRegraEmprestimo;

public class ComandoEmp implements IComando{
    private String codigoUsuario;
    private String codigoLivro;

    public ComandoEmp(String codigoUsuario, String codigoLivro) {
        this.codigoUsuario = codigoUsuario;
        this.codigoLivro = codigoLivro;
    }

    @Override
    public void executar() {
        Repositorio repositorio = Repositorio.getInstance();
        Usuario usuario = repositorio.buscarUsuarioPorCodigo(this.codigoUsuario);
        Livro livro = repositorio.buscarLivroPorCodigo(this.codigoLivro);
        if (usuario == null || livro == null) {
            System.out.println("Usuário ou livro não encontrado.");
            return;
        }
        try{
            IRegraEmprestimo regra = usuario.getRegraEmprestimo();
            regra.validar(usuario, livro);

            Exemplar exemplarDisponivel = livro.getExemplarDisponivel();
            if (exemplarDisponivel != null) {
                livro.removerReserva(usuario);

                Emprestimo emprestimo = new Emprestimo(exemplarDisponivel, usuario);
                usuario.addEmprestimo(emprestimo);
                exemplarDisponivel.setStatus(StatusExemplar.EMPRESTADO);
                exemplarDisponivel.setEmprestimoCorrente(emprestimo);
                System.out.println("Empréstimo do Livro " + livro.getTitulo() + " realizado com sucesso para o usuário " + usuario.getNome() + ".");
            } else {
                System.out.println("Nenhum exemplar disponível do livro " + livro.getTitulo() + ".");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
