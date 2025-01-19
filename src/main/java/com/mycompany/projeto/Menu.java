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
    private List<Integer> existingMarinheiroIDs = new ArrayList<>();
    private List<Integer> existingEmbarcacaoIDs = new ArrayList<>();
    private Porto porto = new Porto("Porto Nautilus");

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
            System.out.println("| 3. RECOVER                           |");
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
                    
                case 3 -> {
                    porto.setMarinherios(RecolhaInfo.LerFicheiroMarinheiros());
                    porto.setEmbarcacoes(RecolhaInfo.LerFicheiroEmbarcacoes());
                }

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
            System.out.println("| 3. Remover Embarcacao                |");
            System.out.println("| 4. Remover Marinheiro                |");
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
                case 1 -> {
                 Embarcacao novaEmbarcacao = RecolhaInfo.criarEmbarcacao(scanner, porto.getEmbarcacoes(), existingEmbarcacaoIDs);
                if (novaEmbarcacao != null) {
                    porto.adicionarEmbarcacao(novaEmbarcacao);
                }
            }
                case 2 -> {
                    Marinheiro novoMarinheiro = RecolhaInfo.criarMarinheiro(scanner, existingMarinheiroIDs);
                    if (novoMarinheiro != null) {
                        porto.adicionarMarinheiro(novoMarinheiro);
                    }
                }
                case 3 ->
                    RecolhaInfo.removerEmbarcacao(scanner, porto.getEmbarcacoes());
                case 4 ->
                    RecolhaInfo.removerMarinheiro(scanner, porto.getMarinherios());
                case 5 ->
                    porto.setMarinherios(RecolhaInfo.LerFicheiroMarinheiros());
                case 6 ->{
                    porto.setEmbarcacoes(RecolhaInfo.LerFicheiroEmbarcacoes());
                }
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
            System.out.println("| 2. Terminar Missao                   |");
            System.out.println("| 3. Info Marinheiros                  |");
            System.out.println("| 4. Info Embarcacoes                  |");
            System.out.println("| 5. Ativar Radar do Porto             |"); //INFO PORTO
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
                    RecolhaInfo.iniciarMissao(scanner, porto);
                case 2 ->
                    RecolhaInfo.terminarMissao(scanner, porto);
                case 3 ->
                    RecolhaInfo.infoMarinheiros(porto.getMarinherios(), scanner);
                case 4 ->
                    RecolhaInfo.verEmbarcacoes(porto.getEmbarcacoes(), scanner);
                case 5 ->
                    RecolhaInfo.ativarRadarDoPorto(porto);
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
                    RecolhaInfo.GuardarInfo(scanner, porto.getMarinherios(), porto.getEmbarcacoes());
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
