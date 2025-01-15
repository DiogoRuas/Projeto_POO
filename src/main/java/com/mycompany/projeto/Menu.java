/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto;

import java.util.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private List<Integer> existingIDs = new ArrayList<>();
    private List<Marinheiro> marinheiros = new ArrayList<>();
    private List<Embarcacao> embarcacoes = new ArrayList<>();

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

                case 0 -> {
                    showFileMenu();
                    // Reseta para nao encerrar apos utilizador mudar de ideias em ShowFileMenu
                    mainOption = 1;
                }
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
            System.out.println("| 2. Adicionar Marinheiro              |");
            System.out.println("| 3. Editar Embarcacao                 |");
            System.out.println("| 4. Remover Embarcacao                |");
            System.out.println("| 5. Recolher Marinheiros de ficheiro  |");
            System.out.println("| 6. Recolher Embarcacoes de ficheiro  |");
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
                case 2 -> {
                    Marinheiro novoMarinheiro = RecolhaInfo.criarMarinheiro(scanner, existingIDs);
                    if (novoMarinheiro != null) {
                        marinheiros.add(novoMarinheiro); // Adiciona o marinheiro à lista
                    }
                }
                case 3 ->
                    System.out.println("Opcao: Remover Embarcacao");
                case 4 ->
                    System.out.println("Opcao: Editar Embarcacao");
                case 5 ->
                    this.marinheiros = RecolhaInfo.LerMarinheiros();
                case 6 ->
                    this.embarcacoes = RecolhaInfo.LerEmbarcacoes();
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
                    RecolhaInfo.infoMarinheiros(marinheiros, scanner);
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

    private void showFileMenu() {
        int subOption;
        do {
            System.out.println("----------------------------------------");
            System.out.println("|          GUARDAR INFORMACAO          |");
            System.out.println("----------------------------------------");
            System.out.println("| 1. Guardar informacao num file.txt   |");
            System.out.println("| 2. Cancelar Sair                     |");
            System.out.println("| 0. Sair                              |");
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
                case 1 -> {
                    RecolhaInfo.GuardarInfo(marinheiros, embarcacoes);
                    System.exit(0);
                }
                case 2 ->
                    System.out.println("Voltando ao menu principal...");
                case 0 -> {
                    System.out.println("A sair do programa...");
                    System.exit(0);
                }
                default ->
                    System.out.println("Opcao invalida!");
            }
        } while (subOption != 2);
    }
}
