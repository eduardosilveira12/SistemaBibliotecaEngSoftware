package br.ufba.mata62.biblioteca.commands;

import br.ufba.mata62.biblioteca.models.Professor;
import br.ufba.mata62.biblioteca.models.Usuario;
import br.ufba.mata62.biblioteca.persistence.Repositorio;

public class ComandoNtf implements IComando {
    private String codigoProfessor;
    public ComandoNtf(String codigoProfessor) {
        this.codigoProfessor = codigoProfessor;
    }
    @Override
    public void executar() {
        Repositorio repositorio = Repositorio.getInstance();
        Usuario usuario = repositorio.buscarUsuarioPorCodigo(this.codigoProfessor);
        if (usuario == null) {
            System.out.println("Usuário com código " + this.codigoProfessor + " não encontrado.");
            return;
        }
        if(usuario instanceof Professor) {
            Professor professor = (Professor) usuario;
            int numeroDeNotificacoes = professor.getContadorNotificacoes();
            System.out.println("O professor " + professor.getNome() + " foi notificado " + numeroDeNotificacoes + " vez(es).");
        } else {
            System.out.println("O usuário informado não é um professor e não pode receber notificações.");
        }
    }

}
