/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

public class RecolhaInfo {

    public static Marinheiro criarMarinheiro(Scanner scanner, List<Integer> existingIDs) {
        String nome = "";
        LocalDate dataNascimento = null;
        Patente patente = null;

        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Digite o nome do marinheiro: ");
                nome = scanner.nextLine();

                System.out.print("Digite a data de nascimento (AAAA-MM-DD): ");
                dataNascimento = LocalDate.parse(scanner.next());

                System.out.print("Digite a patente (OFICIAL, SARGENTO, PRACA): ");
                patente = Patente.valueOf(scanner.next().toUpperCase());

                // Gerar ID único usando GenerateID
                int id = GenerateID.randomUniqueID(1000, 9999, existingIDs);
                existingIDs.add(id); // Adiciona o novo ID à lista de IDs existentes

                // Criar o marinheiro usando o construtor
                Marinheiro novoMarinheiro = new Marinheiro(nome, id, dataNascimento, patente);
                System.out.println("Marinheiro criado com sucesso:");
                System.out.println(novoMarinheiro);

                validInput = true; // Se tudo correr bem, marca como válido
                return novoMarinheiro; // Retorna o marinheiro criado
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                scanner.nextLine(); // Limpa o buffer do scanner
            } catch (Exception e) {
                System.out.println("Entrada invalida! Tente novamente.");
                scanner.nextLine(); // Limpa o buffer do scanner
            }
        }

        return null; // Este ponto nunca deve ser alcançado devido ao loop
    }

    public static void infoMarinheiros(List<Marinheiro> marinheiros, Scanner scanner) {
        if (marinheiros.isEmpty()) {
            System.out.println("Nao ha marinheiros registrados.");
            return;
        }

        // Exibe a lista de marinheiros inicialmente ordenada por nome crescente
        Collections.sort(marinheiros, Comparator.comparing(Marinheiro::getNome)); // Ordena por nome inicialmente
        System.out.println("\n--- Lista de Marinheiros ---");
        for (Marinheiro m : marinheiros) {
            System.out.println(m);
        }

        // Mensagem inicial de ordenação
        String ordemAtual = "Ordenado por nome (crescente)";
        System.out.println(ordemAtual);

        // Menu de opções para ordenar
        int opcaoOrdenacao;
        do {
            System.out.println("\nDeseja ordenar por:");
            System.out.println("(1) ID (crescente)");
            System.out.println("(2) Data de Nascimento (decrescente)");
            System.out.println("(3) Nome (crescente)");
            System.out.println("(0) Voltar ao menu anterior");
            System.out.print("Escolha uma opcao: ");

            if (scanner.hasNextInt()) {
                opcaoOrdenacao = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                switch (opcaoOrdenacao) {
                    case 1 -> {
                        if (!ordemAtual.equals("Ordenado por ID (crescente)")) {
                            Collections.sort(marinheiros, Comparator.comparing(Marinheiro::getId));
                            ordemAtual = "Ordenado por ID (crescente)";
                        } else {
                            System.out.println("Ja esta ordenado por ID (crescente).");
                        }
                    }
                    case 2 -> {
                        if (!ordemAtual.equals("Ordenado por Data de Nascimento (decrescente)")) {
                            Collections.sort(marinheiros, Comparator.comparing(Marinheiro::getdataNascinento).reversed());
                            ordemAtual = "Ordenado por Data de Nascimento (decrescente)";
                        } else {
                            System.out.println("Ja esta ordenado por Data de Nascimento (decrescente).");
                        }
                    }
                    case 3 -> {
                        if (!ordemAtual.equals("Ordenado por nome (crescente)")) {
                            Collections.sort(marinheiros, Comparator.comparing(Marinheiro::getNome));
                            ordemAtual = "Ordenado por nome (crescente)";
                        } else {
                            System.out.println("Ja esta ordenado por nome (crescente).");
                        }
                    }
                    case 0 ->
                        System.out.println("Voltando ao menu anterior...");
                    default ->
                        System.out.println("Opcao invalida! Tente novamente.");
                }

                if (opcaoOrdenacao != 0) {
                    // Exibe a lista ordenada após a escolha do usuário
                    System.out.println("\n--- Lista de Marinheiros Ordenada ---");
                    for (Marinheiro m : marinheiros) {
                        System.out.println(m);
                    }
                    // Exibe a mensagem da ordem atual
                    System.out.println(ordemAtual);
                }

            } else {
                System.out.println("Entrada invalida! Por favor, insira um numero.");
                scanner.nextLine(); // Limpa o buffer do scanner
                opcaoOrdenacao = -1; // Para continuar o loop
            }
        } while (opcaoOrdenacao != 0);
    }
}
