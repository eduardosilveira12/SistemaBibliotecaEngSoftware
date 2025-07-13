package br.ufba.mata62.biblioteca.persistence;

import br.ufba.mata62.biblioteca.models.Livro;
import br.ufba.mata62.biblioteca.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    // estática e privada: padrão Singleton
    private static Repositorio instance;

    private List<Usuario> usuarios;
    private List<Livro> livros;

    private Repositorio() {
        this.usuarios = new ArrayList<>();
        this.livros = new ArrayList<>();
        carregarDadosIniciais();
    }
    public static Repositorio getInstance() {
        if (instance == null) {
            instance = new Repositorio();
        }
        return instance;
    }
    private void carregarDadosIniciais() {
        Usuario joao = new AlunoGraduacao("123", "João da Silva");
        Usuario luiz = new AlunoPosgraduacao("456", "Luiz Fernando Rodrigues");
        Usuario pedro = new AlunoGraduacao("789", "Pedro Paulo");
        Usuario carlos = new Professor("100", "Carlos Lucena");

        this.usuarios.addAll(Arrays.asList(joao, luiz, pedro, carlos));

        Livro engSoftware = new Livro("100", "Engenharia de Software", "Addison Wesley", Arrays.asList("Ian Sommervile"), 6, 2000);
        engSoftware.addExemplar(new Exemplar("01", engSoftware));
        engSoftware.addExemplar(new Exemplar("02", engSoftware));

        Livro umlGuia = new Livro("101", "UML - Guia do Usuário", "Campus", Arrays.asList("Grady Booch", "James Rumbaugh", "Ivar Jacobson"), 7, 2000);
        umlGuia.addExemplar(new Exemplar("03", umlGuia));

        Livro codeComplete = new Livro("200", "Code Complete", "Microsoft Press", Arrays.asList("Steve McConnell"), 2, 2014);
        codeComplete.addExemplar(new Exemplar("04", codeComplete));

        Livro agileDev = new Livro("201", "Agile Software Development, Principles, Patterns and Practices", "Prentice Hall", Arrays.asList("Robert Martin"), 1, 2002);
        agileDev.addExemplar(new Exemplar("05", agileDev));

        Livro refactoring = new Livro("300", "Refactoring: Improving the Design of Existing Code", "Addison Wesley Professional", Arrays.asList("Martin Fowler"), 1, 1999);
        refactoring.addExemplar(new Exemplar("06", refactoring));
        refactoring.addExemplar(new Exemplar("07", refactoring));

        Livro designPatterns = new Livro("400", "Design Patterns: Element of Reusable Object-Oriented Software", "Addison Wesley Professional", Arrays.asList("Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides"), 1, 1994);
        designPatterns.addExemplar(new Exemplar("08", designPatterns));
        designPatterns.addExemplar(new Exemplar("09", designPatterns));

        this.livros.addAll(Arrays.asList(engSoftware, umlGuia, codeComplete, agileDev, refactoring, designPatterns));
    }
    public Usuario buscarUsuarioPorCodigo(String codigo) {
        for (Usuario usuario : this.usuarios) {
            if (usuario.getCodigo().equals(codigo)){
                return usuario;
            }
        }
        return null;
    }
    public Livro buscarLivroPorCodigo(String codigo) {
        for (Livro livro : this.livros) {
            if (livro.getCodigo().equals(codigo)){
                return livro;
            }
        }
        return null;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public List<Livro> getLivros() {
        return livros;
    }
}