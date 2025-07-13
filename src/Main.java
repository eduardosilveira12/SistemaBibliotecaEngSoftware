
import java.util.Scanner;

import br.ufba.mata62.biblioteca.services.ReservaService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o comando:");

        while (true) {
            String linha = scanner.nextLine();
            String[] partes = linha.trim().split("\\s+");

            if (partes.length == 3 && partes[0].equalsIgnoreCase("res")) {
                try {
                    String codigoUsuario = partes[1];
                    String codigoLivro = partes[2];
                    ReservaService.reservarLivro(Integer.parseInt(codigoUsuario), Integer.parseInt(codigoLivro));
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            } else {
                System.out.println("Comando inválido.");
            }
        }
    }
}
