package br.ufba.mata62.biblioteca.ui;

import br.ufba.mata62.biblioteca.commands.*;
import br.ufba.mata62.biblioteca.exceptions.BibliotecaException;
import java.util.Scanner;

public class BibliotecaCLI {

    private IComando parseComando(String input) throws BibliotecaException {
        String[] parts = input.trim().split("\\s+");
        String chaveComando = parts.length > 0 ? parts[0].toLowerCase() : "";

        switch (chaveComando) {
            case "emp":
                if (parts.length != 3) throw new BibliotecaException("Uso correto: emp <código_usuário> <código_livro>");
                return new ComandoEmp(parts[1], parts[2]);
            case "dev":
                if (parts.length != 3) throw new BibliotecaException("Uso correto: dev <código_usuário> <código_livro>");
                return new ComandoDev(parts[1], parts[2]);
            case "res":
                if (parts.length != 3) throw new BibliotecaException("Uso correto: res <código_usuário> <código_livro>");
                return new ComandoRes(parts[1], parts[2]);
            case "obs":
                if (parts.length != 3) throw new BibliotecaException("Uso correto: obs <código_usuário> <código_livro>");
                return new ComandoObs(parts[1], parts[2]);
            case "liv":
                if (parts.length != 2) throw new BibliotecaException("Uso correto: liv <código_livro>");
                return new ComandoLiv(parts[1]);
            case "usu":
                if (parts.length != 2) throw new BibliotecaException("Uso correto: usu <código_usuário>");
                return new ComandoUsu(parts[1]);
            case "ntf":
                if (parts.length != 2) throw new BibliotecaException("Uso correto: ntf <código_professor>");
                return new ComandoNtf(parts[1]);
            case "sai":
                return null;
            default:
                throw new BibliotecaException("Comando '" + chaveComando + "' desconhecido.");
        }
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sistema de Biblioteca da UFBA - MATA62");
        System.out.println("Digite um comando ou 'sai' para terminar.");

        while (true) {
            try {
                System.out.print("> ");
                String input = scanner.nextLine();

                IComando comando = parseComando(input);

                if (comando == null) {
                    break;
                }

                comando.executar();

                if (!(comando instanceof ComandoLiv || comando instanceof ComandoUsu || comando instanceof ComandoNtf)) {
                    System.out.println("Operação realizada com sucesso.");
                }

            } catch (BibliotecaException e) {
                System.out.println("[ERRO] " + e.getMessage());
            } catch (Exception e) {
                System.out.println("[ERRO INESPERADO] Ocorreu um problema: " + e.getMessage());
            }
        }

        System.out.println("Sistema finalizado.");
        scanner.close();
    }
}