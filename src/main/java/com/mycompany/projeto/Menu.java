/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto;

import java.util.Scanner;

/**
 *
 * @author maria
 */
public class Menu {

    private final Scanner scanner = new Scanner(System.in);

    public Menu() {
    }

    public void showMenu() {
        int mainOption;

        do {
            System.out.println("----------------------------------------");
            System.out.println("|             MENU PRINCIPAL           |");
            System.out.println("----------------------------------------");
            System.out.println("| 1. Modo Manutencao                   |");
            System.out.println("| 2. Modo Utilizacao                   |");
            System.out.println("| 0. Sair                              |");
            System.out.println("----------------------------------------");
            System.out.print("Escolha uma opcao: ");

            if (scanner.hasNextInt()) {
                mainOption = scanner.nextInt();
                System.out.println("/////////////////////---------------///////////////////");
                System.out.println("/////////////////////---------------///////////////////");

                scanner.nextLine();
            } else {
                System.out.println("Entrada invalida! Por favor, insira um numero.");
                System.out.println("/////////////////////---------------///////////////////");

                scanner.nextLine();
                mainOption = -1;
                continue;
            }

            switch (mainOption) {
                case 1 ->
                    showMaintenanceMenu();

                case 2 ->
                    showUsageMenu();

                case 0 ->
                    System.out.println("Saindo do programa...");

                default ->
                    System.out.println("Opcao invalida!");
            }
        } while (mainOption != 0);
    }

    private void showMaintenanceMenu() {
        int subOption;
        do {
            System.out.println("----------------------------------------");
            System.out.println("|           MODO MANUTENCAO            |");
            System.out.println("----------------------------------------");
            System.out.println("| 1. Criar Embarcacao                  |");
            System.out.println("| 2. Editar Embarcacao                 |");
            System.out.println("| 3. Remover Embarcacao                |");
            System.out.println("| 0. Voltar                            |");
            System.out.println("----------------------------------------");
            System.out.print("Escolha uma opcao: ");

            if (scanner.hasNextInt()) {
                subOption = scanner.nextInt();
                scanner.nextLine();
                System.out.println("/////////////////////---------------///////////////////");
                System.out.println("/////////////////////---------------///////////////////");

            } else {
                System.out.println("Entrada invalida! Por favor, insira um numero.");
                System.out.println("/////////////////////---------------///////////////////");

                scanner.nextLine();
                subOption = -1;
                continue;
            }

            switch (subOption) {
                case 1 ->
                    System.out.println("Opcao: Criar Embarcacao");
                case 2 ->
                    System.out.println("Opcao: Editar Embarcacao");
                case 3 ->
                    System.out.println("Opcao: Remover Embarcacao");
                case 0 ->
                    System.out.println("Voltando ao menu principal...");
                default ->
                    System.out.println("Opcao invalida!");
            }
        } while (subOption != 0);
    }

    private void showUsageMenu() {
        int subOption;
        do {
            System.out.println("----------------------------------------");
            System.out.println("|           MODO UTILIZACAO            |");
            System.out.println("----------------------------------------");
            System.out.println("| 1. Iniciar Missao                    |");
            System.out.println("| 2. Info Marinheiros                  |");
            System.out.println("| 3. Info Embarcacoes                  |");
            System.out.println("| 4. Ver Embarcacoes                   |");
            System.out.println("| 0. Voltar                            |");
            System.out.println("----------------------------------------");
            System.out.print("Escolha uma opcao: ");

            if (scanner.hasNextInt()) {
                subOption = scanner.nextInt();
                scanner.nextLine();
                System.out.println("/////////////////////---------------///////////////////");
                System.out.println("/////////////////////---------------///////////////////");

            } else {
                System.out.println("Entrada invalida! Por favor, insira um numero.");
                System.out.println("/////////////////////---------------///////////////////");

                scanner.nextLine();
                subOption = -1;
                continue;
            }

            switch (subOption) {
                case 1 ->
                    System.out.println("Opcao: Iniciar Missao");
                case 2 ->
                    System.out.println("Opcao: Info Marinheiros");
                case 3 ->
                    System.out.println("Opcao: Info Embarcacoes");
                case 4 ->
                    System.out.println("Opcao: Ver Embarcacoes");
                case 0 ->
                    System.out.println("Voltando ao menu principal...");
                default ->
                    System.out.println("Opcao invalida!");

            }
        } while (subOption != 0);
    }

}
