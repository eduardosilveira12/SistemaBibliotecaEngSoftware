import br.ufba.mata62.biblioteca.persistence.Repositorio;
import br.ufba.mata62.biblioteca.ui.BibliotecaCLI;

public class Main {
    public static void main(String[] args) {
        Repositorio.getInstance();
        BibliotecaCLI cli = new BibliotecaCLI();
        cli.iniciar();
    }
}