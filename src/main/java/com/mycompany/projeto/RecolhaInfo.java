/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author diogo
 */
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
    
    public static void infoEmbarcacoes(List<Embarcacao> embarcacoes, Scanner scanner) {
        if (embarcacoes.isEmpty()) {
            System.out.println("Nao ha embarcacoes registrados.");
            return;
        }
        for (Embarcacao e : embarcacoes) {
            System.out.println(e);
        }
    }

    // FILE WRITING
    public static void GuardarInfo(List<Marinheiro> marinheiros, List<Embarcacao> embarcacoes) {
        if (marinheiros.isEmpty() && embarcacoes.isEmpty()) {
            System.out.println("Nao ha marinheiros registrados.");
            return;
        }

        String informacaoMarinheiros = "";
        String informacaoEmbarcacoes = "";

        for (Marinheiro m : marinheiros) {
            informacaoMarinheiros += m.toString() + "\n";
        }
        for (Embarcacao e : embarcacoes) {
            informacaoEmbarcacoes += e.toString() + "\n";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write("Lista de embarcacoes:\n");
            writer.write(informacaoEmbarcacoes);
            writer.write("\n");
            writer.write("Lista de marinheiros:\n");
            writer.write(informacaoMarinheiros);
            System.out.println("Informacoes salvas com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // FILE READING
    public static List<Marinheiro> LerMarinheiros() {

        Scanner scanner = new Scanner(System.in);
        List<Marinheiro> marinheiros = new ArrayList<>();

        System.out.print("Insira o nome do ficheiro para ler os marinheiros: ");
        String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Marinheiro[")) {
                    // Extrair os campos do texto
                    String[] parts = line.replace("Marinheiro[", "").replace("]", "").split(", ");
                    int id = Integer.parseInt(parts[0].split("=")[1]);
                    String nome = parts[1].split("=")[1];
                    LocalDate dataNascimento = LocalDate.parse(parts[2].split("=")[1]);
                    Patente patente = Patente.valueOf(parts[4].split("=")[1]);
                    boolean inMissao = Boolean.parseBoolean(parts[5].split("=")[1]);

                    Marinheiro marinheiro = new Marinheiro(nome, id, dataNascimento, patente);
                    marinheiro.setInMissao(inMissao);
                    marinheiros.add(marinheiro);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return marinheiros;
    }

    public static List<Embarcacao> LerEmbarcacoes() {
        
        Scanner scanner = new Scanner(System.in);
        List<Embarcacao> embarcacoes = new ArrayList<>();

        System.out.print("Insira o nome do ficheiro para ler as embarcacoes: ");
        String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("BarcoPatrulha[")) {

                    String[] parts = line.replace("BarcoPatrulha[", "").replace("]", "").split(", motor=");
                    String embarcacaoInfo = parts[0];
                    String motorInfo = parts[1];

                    String[] embarcacaoParts = embarcacaoInfo.split(", ");
                    int id = Integer.parseInt(embarcacaoParts[0].split("=")[1]);
                    String nome = embarcacaoParts[1].split("=")[1];
                    String marca = embarcacaoParts[2].split("=")[1];
                    String modelo = embarcacaoParts[3].split("=")[1];
                    LocalDate dataFabricacao = LocalDate.parse(embarcacaoParts[4].split("=")[1]);

                    // Metodo auxiliar para ler as partes do motor
                    Motor motor = LerMotor(motorInfo);

                    BarcoPatrulha barco = new BarcoPatrulha(id, nome, marca, motor, modelo, dataFabricacao);
                    embarcacoes.add(barco);
                    
                } 
                else if (line.startsWith("NavioSuporte[")) {

                    String[] parts = line.replace("NavioSuporte[", "").replace("]", "").split(", motores=");
                    String embarcacaoInfo = parts[0];
                    String motoresInfo = parts[1].split(", capacidadeCarga=")[0];

                    String[] embarcacaoParts = embarcacaoInfo.split(", ");
                    int id = Integer.parseInt(embarcacaoParts[0].split("=")[1]);
                    String nome = embarcacaoParts[1].split("=")[1];
                    String marca = embarcacaoParts[2].split("=")[1];
                    String modelo = embarcacaoParts[3].split("=")[1];
                    LocalDate dataFabricacao = LocalDate.parse(embarcacaoParts[4].split("=")[1]);

                    String[] motoresArray = motoresInfo.split("; ");
                    ArrayList<Motor> motores = new ArrayList<>();
                    for (String motorString : motoresArray) {
                        if (!motorString.isBlank()) {
                            motores.add(LerMotor(motorString));
                        }
                    }

                    String capacidadeCarga = parts[1].split(", numCamas=")[1];
                    int capacidade = Integer.parseInt(capacidadeCarga.split(",")[0]);
                    int numCamas = Integer.parseInt(parts[1].split("numCamas=")[1].split(",")[0]);
                    int botes = Integer.parseInt(parts[1].split("botesSalvaVidas=")[1]);

                    NavioSuporte navio = new NavioSuporte(id, nome, marca, modelo, dataFabricacao, motores, capacidade, numCamas, botes);
                    embarcacoes.add(navio);

                } 
                else if (line.startsWith("LanchaRapida[")) {

                    String[] parts = line.replace("LanchaRapida[", "").replace("]", "").split(", motores=");
                    String embarcacaoInfo = parts[0];
                    String motoresInfo = parts[1];

                    String[] embarcacaoParts = embarcacaoInfo.split(", ");
                    int id = Integer.parseInt(embarcacaoParts[0].split("=")[1]);
                    String nome = embarcacaoParts[1].split("=")[1];
                    String marca = embarcacaoParts[2].split("=")[1];
                    String modelo = embarcacaoParts[3].split("=")[1];
                    LocalDate dataFabricacao = LocalDate.parse(embarcacaoParts[4].split("=")[1]);

                    String[] motoresArray = motoresInfo.split("; ");
                    ArrayList<Motor> motores = new ArrayList<>();
                    for (String motorString : motoresArray) {
                        if (!motorString.isBlank()) {
                            motores.add(LerMotor(motorString));
                        }
                    }

                    LanchaRapida lancha = new LanchaRapida(id, nome, marca, modelo, dataFabricacao, motores);
                    embarcacoes.add(lancha);
                } else {
                    throw new IllegalArgumentException("Tipo desconhecido de embarcação: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
        }

        return embarcacoes;
    }

    private static Motor LerMotor(String motorInfo) {
        motorInfo = motorInfo.replace("Motor {", "").replace("}", "").trim();
        String[] parts = motorInfo.split(", ");

        int potencia = Integer.parseInt(parts[0].split("=")[1].replace(" cv", ""));
        double cilindrada = Double.parseDouble(parts[1].split("=")[1].replace(" cm³", ""));
        double fuelTankCapacity = Double.parseDouble(parts[2].split("=")[1].replace(" litros", ""));
        String combustivel = parts[3].split("=")[1].replace("'", "");
        int consumo = Integer.parseInt(parts[4].split("=")[1].replace(" L/h", ""));

        return new Motor(potencia, cilindrada, fuelTankCapacity, combustivel, consumo);
    }
}
