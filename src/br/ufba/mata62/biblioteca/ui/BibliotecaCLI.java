package br.ufba.mata62.biblioteca.ui;

import br.ufba.mata62.biblioteca.commands.*;
import br.ufba.mata62.biblioteca.exceptions.BibliotecaException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BibliotecaCLI {

    private final Map<String, IComandoSupplier> commandSuppliers;

    public BibliotecaCLI() {
        this.commandSuppliers = new HashMap<>();

        commandSuppliers.put("emp", params -> {
            if (params.length != 2) throw new BibliotecaException("Uso correto: emp <código_usuário> <código_livro>");
            return new ComandoEmp(params[0], params[1]);
        });
        commandSuppliers.put("dev", params -> {
            if (params.length != 2) throw new BibliotecaException("Uso correto: dev <código_usuário> <código_livro>");
            return new ComandoDev(params[0], params[1]);
        });
        commandSuppliers.put("res", params -> {
            if (params.length != 2) throw new BibliotecaException("Uso correto: res <código_usuário> <código_livro>");
            return new ComandoRes(params[0], params[1]);
        });
        commandSuppliers.put("obs", params -> {
            if (params.length != 2) throw new BibliotecaException("Uso correto: obs <código_usuário> <código_livro>");
            return new ComandoObs(params[0], params[1]);
        });
        commandSuppliers.put("liv", params -> {
            if (params.length != 1) throw new BibliotecaException("Uso correto: liv <código_livro>");
            return new ComandoLiv(params[0]);
        });
        commandSuppliers.put("usu", params -> {
            if (params.length != 1) throw new BibliotecaException("Uso correto: usu <código_usuário>");
            return new ComandoUsu(params[0]);
        });
        commandSuppliers.put("ntf", params -> {
            if (params.length != 1) throw new BibliotecaException("Uso correto: ntf <código_professor>");
            return new ComandoNtf(params[0]);
        });
    }

    private IComando parseComando(String input) throws BibliotecaException {
        String[] parts = input.trim().split("\\s+");
        String chaveComando = parts.length > 0 ? parts[0].toLowerCase() : "";

        if (chaveComando.equals("sai")) {
            return null;
        }

        IComandoSupplier supplier = commandSuppliers.get(chaveComando);

        if (supplier == null) {
            throw new BibliotecaException("Comando '" + chaveComando + "' desconhecido.");
        }

        String[] params = new String[parts.length - 1];
        System.arraycopy(parts, 1, params, 0, params.length);

        return supplier.create(params);
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