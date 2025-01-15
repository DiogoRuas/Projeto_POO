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
import java.util.Map;
import java.util.HashMap;

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
                System.out.println(novoMarinheiro.toString());

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

    public static Embarcacao criarEmbarcacao(Scanner scanner, List<Embarcacao> embarcacoes, List<Integer> existingIDs) {
        System.out.println("Criar Embarcacao");
        int id = GenerateID.randomUniqueID(1, 1000, existingIDs);

        String nome = lerNome(scanner, embarcacoes);
        String marca = lerMarca(scanner);
        String modelo = lerModelo(scanner);
        LocalDate dataFabricacao = lerDataFabricacao(scanner);

        int tipoEmbarcacao = lerTipoEmbarcacao(scanner);
        
        switch (tipoEmbarcacao) {
            case 1 -> {
                Motor motor = lerMotor(scanner);
                BarcoPatrulha novaEmbarcacao = new BarcoPatrulha(id, nome, marca, motor, modelo, dataFabricacao);
                System.out.println("Embarcacao criada com sucesso:");
                System.out.printf(novaEmbarcacao.toString() + "\n");
                
                return novaEmbarcacao;
            }
            case 2 -> {
                ArrayList<Motor> motores = new ArrayList<>();
                int numMotores = lerNumMotores(scanner, 2, 4);
                for (int i = 0; i < numMotores; i++) {
                    motores.add(lerMotor(scanner));
                }
                LanchaRapida novaEmbarcacao = new LanchaRapida(id, nome, marca, modelo, dataFabricacao, motores);
                System.out.println("Embarcacao criada com sucesso:");
                System.out.printf(novaEmbarcacao.toString() + "\n");
                
                return novaEmbarcacao;
            }
            case 3 -> {
                ArrayList<Motor> motores = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    Motor motor;
                    do {
                        System.out.println("Motor " + (i + 1) + " do Navio de Suporte:");
                        motor = lerMotor(scanner);
                        if (motor.getPotencia() <= 25000) {
                            System.out.println("A potencia do motor deve ser superior a 25000cv. Tente novamente.");
                        }
                    } while (motor.getPotencia() <= 25000);
                    motores.add(motor);
                }
                
                int capacidadeCarga = lerCapacidadeCarga(scanner);
                int numCamas = lerNumCamas(scanner);
                int botesSalvaVidas = lerBotesSalvaVidas(scanner);
                NavioSuporte novaEmbarcacao = new NavioSuporte(id, nome, marca, modelo, dataFabricacao, motores, capacidadeCarga, numCamas, botesSalvaVidas);
                System.out.println("Embarcacao criada com sucesso:");
                System.out.printf(novaEmbarcacao.toString() + "\n");
                
                return novaEmbarcacao;
            }
            default ->
                throw new IllegalArgumentException("Tipo de embarcacao invalido.");
        }
    }

    private static Motor lerMotor(Scanner scanner) {
        int potencia = 0;
        double cilindrada = 0;
        double fuelTankCapacity = 0;
        String combustivel = "";
        int consumo = 0;

        // Validação da potência
        while (true) {
            try {
                System.out.print("Potencia (cv): ");
                potencia = Integer.parseInt(scanner.nextLine());
                if (potencia <= 0) {
                    System.out.println("A potencia deve ser maior que zero. Tente novamente.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um valor numerico valido para a potencia.");
            }
        }

        // Validação da cilindrada
        while (true) {
            try {
                System.out.print("Cilindrada (cm3): ");
                cilindrada = Double.parseDouble(scanner.nextLine());
                if (cilindrada <= 0) {
                    System.out.println("A cilindrada deve ser maior que zero. Tente novamente.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um valor numerico valido para a cilindrada.");
            }
        }

        // Validação da capacidade do tanque
        while (true) {
            try {
                System.out.print("Capacidade do Tanque (litros): ");
                fuelTankCapacity = Double.parseDouble(scanner.nextLine());
                if (fuelTankCapacity <= 0) {
                    System.out.println("A capacidade do tanque deve ser maior que zero. Tente novamente.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um valor numerico valido para a capacidade do tanque.");
            }
        }

        // Validação do tipo de combustível
        while (true) {
            System.out.print("Tipo de Combustivel: ");
            combustivel = scanner.nextLine().trim();
            if (!combustivel.matches("[a-zA-Z]+")) { // Verifica se contém apenas letras
                System.out.println("O tipo de combustivel deve conter apenas letras. Tente novamente.");
            } else {
                break;
            }
        }

        // Validação do consumo
        while (true) {
            try {
                System.out.print("Consumo (L/h): ");
                consumo = Integer.parseInt(scanner.nextLine());
                if (consumo <= 0) {
                    System.out.println("O consumo deve ser maior que zero. Tente novamente.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um valor numerico valido para o consumo.");
            }
        }

        return new Motor(potencia, cilindrada, fuelTankCapacity, combustivel, consumo);
    }

    private static String lerNome(Scanner scanner, List<Embarcacao> embarcacoes) {
        while (true) {
            System.out.print("Nome da embarcacao: ");
            String nome = scanner.nextLine().trim();

            // Verifica se o nome é nulo ou vazio
            if (nome.isEmpty()) {
                throw new IllegalArgumentException("O nome da embarcacao nao pode ser nulo ou vazio.");
            }

            // Verifica se já existe uma embarcação com o mesmo nome
            for (Embarcacao e: embarcacoes){
                if(e.getNome().equals(nome)){
                    throw new IllegalArgumentException("Ja existe uma embarcacao com o nome '" + nome + "'.");
                }
            }

            return nome;
        }
    }

    private static String lerMarca(Scanner scanner) {
        System.out.print("Marca: ");
        return scanner.nextLine().trim();
    }

    private static String lerModelo(Scanner scanner) {
        System.out.print("Modelo: ");
        return scanner.nextLine().trim();
    }

    private static LocalDate lerDataFabricacao(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Data de fabricacao (YYYY-MM-DD): ");
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Formato de data invalido. Tente novamente.");
            }
        }
    }

    private static Zona lerZona(Scanner scanner) {
        while (true) {
            System.out.println("Zonas disponiveis: NORTE, SUL, ESTE, OESTE");
            System.out.print("Zona: ");
            String zonaInput = scanner.nextLine().trim().toUpperCase();
            try {
                return Zona.valueOf(zonaInput); // Tenta converter a string para o enum
            } catch (IllegalArgumentException e) {
                System.out.println("Zona invalida. Tente novamente.");
            }
        }
    }

    private static int lerTipoEmbarcacao(Scanner scanner) {
        while (true) {
            System.out.println("Tipos de embarcacao:");
            System.out.println("1 - Barco de Patrulha");
            System.out.println("2 - Lancha Rapida");
            System.out.println("3 - Navio de Suporte");
            System.out.print("Escolha o tipo (1-3): ");
            try {
                int tipo = Integer.parseInt(scanner.nextLine().trim());
                if (tipo >= 1 && tipo <= 3) {
                    return tipo;
                }
                System.out.println("Opção invalida. Escolha entre 1 e 3.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite um numero.");
            }
        }
    }

    private static int lerNumMotores(Scanner scanner, int min, int max) {
        while (true) {
            System.out.printf("Numero de motores (%d-%d): ", min, max);
            try {
                int num = Integer.parseInt(scanner.nextLine().trim());
                if (num >= min && num <= max) {
                    return num;
                }
                System.out.printf("O numero deve estar entre %d e %d. Tente novamente.\n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite um numero.");
            }
        }
    }

    private static int lerCapacidadeCarga(Scanner scanner) {
        System.out.print("Capacidade de carga (kg): ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static int lerNumCamas(Scanner scanner) {
        System.out.print("Numero de camas hospitalares: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static int lerBotesSalvaVidas(Scanner scanner) {
        System.out.print("Numero de botes salva-vidas: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static void removerEmbarcacao(Scanner scanner, List<Embarcacao> embarcacoes) {
        if (embarcacoes.isEmpty()) {
            System.out.println("Nao ha embarcacoes disponiveis para remover.");
            return;
        }

        // Agrupar embarcações por zona
        Map<Zona, List<Embarcacao>> embarcacoesPorZona = new HashMap<>();
        for (Embarcacao e : embarcacoes) {
            embarcacoesPorZona.computeIfAbsent(e.getZona(), k -> new ArrayList<>()).add(e);
        }

        // Exibir as embarcações organizadas por zona e ordenadas por ID
        for (Zona zona : Zona.values()) {
            List<Embarcacao> listaEmbarcacoes = embarcacoesPorZona.get(zona);
            System.out.println("Zona " + zona + ":");
            if (listaEmbarcacoes == null || listaEmbarcacoes.isEmpty()) {
                System.out.println("- Sem registo");
            } else {
                // Ordenar a lista de embarcações por ID
                listaEmbarcacoes.sort(Comparator.comparingInt(Embarcacao::getId));
                for (int i = 0; i < listaEmbarcacoes.size(); i++) {
                    Embarcacao e = listaEmbarcacoes.get(i);
                    System.out.printf("%d: ID: %d - %s - %s - %s%n", i + 1, e.getId(), e.getNome(), e.getMarca(), e.getModelo());
                }
            }
        }

        System.out.print("Escolha o numero da embarcacao que deseja remover: ");
        int index = scanner.nextInt() - 1; // ajusta o indice
        scanner.nextLine();

        // verifica se o indice está correto e remove a embarcação
        if (index >= 0 && index < embarcacoes.size()) {
            Embarcacao removedEmbarcacao = embarcacoes.remove(index);
            System.out.printf("Embarcacao '%s' removida com sucesso.%n", removedEmbarcacao.getNome());
        } else {
            System.out.println("Indice invalido! Nenhuma embarcacao foi removida.");
        }
    }

    public static void removerMarinheiro(Scanner scanner, List<Marinheiro> marinheiros) {
        if (marinheiros.isEmpty()) {
            System.out.println("Nao ha marinheiros disponiveis para remover.");
            return;
        }

        System.out.println("Marinheiros disponiveis:");
        for (int i = 0; i < marinheiros.size(); i++) {
            Marinheiro m = marinheiros.get(i);
            // Exiba as informações relevantes do marinheiro, incluindo ID
            System.out.printf("%d: ID: %d - Nome: %s%n", i + 1, m.getId(), m.getNome());
        }

        // Solicitar ao usuário que escolha um marinheiro para remover
        System.out.print("Escolha o numero do marinheiro que deseja remover: ");
        int index = scanner.nextInt() - 1; // Ajusta o índice para zero-based
        scanner.nextLine(); // Limpa o buffer

        if (index >= 0 && index < marinheiros.size()) {
            Marinheiro removedMarinheiro = marinheiros.remove(index);
            System.out.printf("Marinheiro '%s' removido com sucesso.%n", removedMarinheiro.getNome());
        } else {
            System.out.println("Indice invalido! Nenhum marinheiro foi removido.");
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
    public static List<Marinheiro> LerFicheiroMarinheiros() {

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

    public static List<Embarcacao> LerFicheiroEmbarcacoes() {
        Scanner scanner = new Scanner(System.in);
        List<Embarcacao> embarcacoes = new ArrayList<>();

        System.out.print("Insira o nome do ficheiro para ler as embarcacoes: ");
        String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("BarcoPatrulha[") || line.startsWith("LanchaRapida[") || line.startsWith("NavioSuporte[")) {
                    // Separar os atributos principais e as informações de motor/motores
                    int motorIndex = line.lastIndexOf("motor=");
                    int motoresIndex = line.lastIndexOf("motores=");
                    String mainInfo = line.substring(0, motorIndex != -1 ? motorIndex : motoresIndex).replace("[", "").replace("]", "").trim();
                    String motorInfo = motorIndex != -1 ? line.substring(motorIndex).replace("]", "").trim() : "";
                    String motoresInfo = motoresIndex != -1 ? line.substring(motoresIndex).replace("]", "").trim() : "";

                    // Dividir os campos principais
                    String[] parts = mainInfo.split(", ");
                    int id = Integer.parseInt(parts[0].split("=")[1]);
                    String nome = parts[1].split("=")[1];
                    String marca = parts[2].split("=")[1];
                    String modelo = parts[3].split("=")[1];
                    String dataFabricacaoRaw = parts[4].split("=")[1].trim();
                    dataFabricacaoRaw = dataFabricacaoRaw.endsWith(",") ? dataFabricacaoRaw.substring(0, dataFabricacaoRaw.length() - 1) : dataFabricacaoRaw;
                    LocalDate dataFabricacao = LocalDate.parse(dataFabricacaoRaw);

                    if (line.startsWith("BarcoPatrulha[")) {
                        Motor motor = parseMotor(motorInfo.replace("motor=", ""));
                        BarcoPatrulha barcoPatrulha = new BarcoPatrulha(id, nome, marca, motor, modelo, dataFabricacao);
                        embarcacoes.add(barcoPatrulha);
                    } else if (line.startsWith("LanchaRapida[")) {
                        ArrayList<Motor> motores = parseMotores(motoresInfo.replace("motores=", ""));
                        LanchaRapida lanchaRapida = new LanchaRapida(id, nome, marca, modelo, dataFabricacao, motores);
                        embarcacoes.add(lanchaRapida);
                    } else if (line.startsWith("NavioSuporte[")) {
                        ArrayList<Motor> motores = parseMotores(motoresInfo.replace("motores=", ""));
                        int capacidadeCarga = Integer.parseInt(parts[5].split("=")[1]);
                        int numCamas = Integer.parseInt(parts[6].split("=")[1]);
                        int botes = Integer.parseInt(parts[7].split("=")[1]);
                        NavioSuporte navioSuporte = new NavioSuporte(id, nome, marca, modelo, dataFabricacao, motores, capacidadeCarga, numCamas, botes);
                        embarcacoes.add(navioSuporte);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return embarcacoes;
    }

    // Método auxiliar para parsear informações de um motor
    private static Motor parseMotor(String motorInfo) {
        String[] motorParts = motorInfo.replace("Motor {", "").replace("}", "").split(", ");
        int potencia = Integer.parseInt(motorParts[0].split("=")[1]);
        double cilindrada = Double.parseDouble(motorParts[1].split("=")[1]);
        double fuelTankCapacity = Double.parseDouble(motorParts[2].split("=")[1]);
        String combustivel = motorParts[3].split("=")[1];
        int consumo = Integer.parseInt(motorParts[4].split("=")[1]);
        return new Motor(potencia, cilindrada, fuelTankCapacity, combustivel, consumo);
    }

    // Método auxiliar para parsear uma lista de motores
    private static ArrayList<Motor> parseMotores(String motoresInfo) {
        ArrayList<Motor> motores = new ArrayList<>();
        String[] motoresArray = motoresInfo.split("; ");
        for (String motorInfo : motoresArray) {
            if (!motorInfo.isBlank()) {
                motores.add(parseMotor(motorInfo));
            }
        }
        return motores;
    }
}
