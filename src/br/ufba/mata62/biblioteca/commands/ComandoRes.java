package br.ufba.mata62.biblioteca.commands;

import br.ufba.mata62.biblioteca.models.Livro;
import br.ufba.mata62.biblioteca.models.Reserva;
import br.ufba.mata62.biblioteca.models.Usuario;
import br.ufba.mata62.biblioteca.persistence.Repositorio;

public class ComandoRes implements IComando {
    private String codigoUsuario;
    private String codigoLivro;

    public ComandoRes(String codigoUsuario, String codigoLivro) {
        this.codigoUsuario = codigoUsuario;
        this.codigoLivro = codigoLivro;
    }
    @Override
    public void executar() {
        Repositorio repositorio = Repositorio.getInstance();
        Usuario usuario = repositorio.buscarUsuarioPorCodigo(this.codigoUsuario);
        Livro livro = repositorio.buscarLivroPorCodigo(this.codigoLivro);
        if (usuario != null || livro != null) {
            System.out.println("Usuário ou livro não encontrado.");
            return;
        }
        if(livro.usuarioTemReserva(usuario) || usuario.temEmprestimoDoLivro(livro.getCodigo())) {
            System.out.println("Não foi possível realizar a reserva. Usuário já possui reserva ou empréstimo ativo para este livro.");
            return;
        }
        Reserva novaReserva = new Reserva(usuario, livro);
        livro.addReserva(novaReserva);
        System.out.println("Reserva do livro '" + livro.getTitulo() + "' realizada com sucesso para o usuário '" + usuario.getNome() + "'.");
    }
}
