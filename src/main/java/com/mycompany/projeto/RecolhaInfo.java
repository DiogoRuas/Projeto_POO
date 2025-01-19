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

                // Gerar ID único com o GenerateID
                int id = GenerateID.randomUniqueID(1000, 9999, existingIDs);
                existingIDs.add(id); // Adiciona o novo ID à lista de IDs existentes

                // Criar o marinheiro 
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

        // Ordenação inicial por nome  
        Collections.sort(marinheiros, new Comparator<Marinheiro>() {
            @Override
            public int compare(Marinheiro m1, Marinheiro m2) {
                return m1.getNome().compareToIgnoreCase(m2.getNome());
            }
        });

        System.out.println("\n--- Lista de Marinheiros ---");
        for (Marinheiro m : marinheiros) {
            System.out.println(m);
        }

        // Mensagem inicial de ordenação
        String ordemAtual = "Ordenado por nome (crescente)";
        System.out.println(ordemAtual);

        // Menu de opções para ordenar. O user escolhe como quer ordenar
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
                scanner.nextLine(); // Consumir nova linha no scanne

                switch (opcaoOrdenacao) {
                    case 1 -> {
                        if (!ordemAtual.equals("Ordenado por ID (crescente)")) {
                            Collections.sort(marinheiros, new Comparator<Marinheiro>() {
                                @Override
                                public int compare(Marinheiro m1, Marinheiro m2) {
                                    return Integer.compare(m1.getId(), m2.getId());
                                }
                            });
                            ordemAtual = "Ordenado por ID (crescente)";
                        } else {
                            System.out.println("Ja esta ordenado por ID (crescente).");
                        }
                    }
                    case 2 -> {
                        if (!ordemAtual.equals("Ordenado por Data de Nascimento (decrescente)")) {
                            Collections.sort(marinheiros, new Comparator<Marinheiro>() {
                                @Override
                                public int compare(Marinheiro m1, Marinheiro m2) {
                                    return m2.getdataNascinento().compareTo(m1.getdataNascinento());
                                }
                            });
                            ordemAtual = "Ordenado por Data de Nascimento (decrescente)";
                        } else {
                            System.out.println("Ja esta ordenado por Data de Nascimento (decrescente).");
                        }
                    }
                    case 3 -> {
                        if (!ordemAtual.equals("Ordenado por nome (crescente)")) {
                            Collections.sort(marinheiros, new Comparator<Marinheiro>() {
                                @Override
                                public int compare(Marinheiro m1, Marinheiro m2) {
                                    return m1.getNome().compareToIgnoreCase(m2.getNome());
                                }
                            });
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
                scanner.nextLine();
                opcaoOrdenacao = -1; // Para continuar o loop
            }
        } while (opcaoOrdenacao != 0);
    }

    public static void verEmbarcacoes(List<Embarcacao> embarcacoes, Scanner scanner) {
        if (embarcacoes.isEmpty()) {
            System.out.println("Nao ha embarcacoes registradas.");
            return;
        }

        Map<Zona, List<Embarcacao>> embarcacoesPorZona = new HashMap<>();
        for (Zona zona : Zona.values()) {
            embarcacoesPorZona.put(zona, new ArrayList<>());
        }

        for (Embarcacao e : embarcacoes) {
            embarcacoesPorZona.get(e.getZona()).add(e);
        }

        String ordemAtual = "Ordenado por ID (crescente)";

        int opcao;
        do {
            System.out.println("\n--- Embarcacoes disponiveis ---");
            System.out.println(ordemAtual);

            for (Zona zona : Zona.values()) {
                System.out.println("\nZONA " + zona + ":");
                List<Embarcacao> embarcacoesNaZona = embarcacoesPorZona.get(zona);
                if (embarcacoesNaZona.isEmpty()) {
                    System.out.println("- SEM REGISTO");
                } else {
                    for (int i = 0; i < embarcacoesNaZona.size(); i++) {
                        Embarcacao embarcacao = embarcacoesNaZona.get(i);
                        String status = embarcacao.isInMissao() ? " (Em missao)" : " (Disponivel)";
                        System.out.printf("%d: %s%s\n", i + 1, embarcacao.toString(), status);
                    }
                }
            }

            System.out.println("\nDeseja ordenar por:");
            System.out.println("1. ID (crescente)");
            System.out.println("2. Marca (crescente)");
            System.out.println("3. Ano de fabricação (decrescente)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    ordemAtual = "Ordenado por ID (crescente)";
                    for (List<Embarcacao> lista : embarcacoesPorZona.values()) {
                        Collections.sort(lista, Comparator.comparingInt(Embarcacao::getId));
                    }
                }
                case 2 -> {
                    ordemAtual = "Ordenado por Marca (crescente)";
                    for (List<Embarcacao> lista : embarcacoesPorZona.values()) {
                        Collections.sort(lista, Comparator.comparing(Embarcacao::getMarca));
                    }
                }
                case 3 -> {
                    ordemAtual = "Ordenado por Ano de fabricacao (decrescente)";
                    for (List<Embarcacao> lista : embarcacoesPorZona.values()) {
                        Collections.sort(lista, Comparator.comparing(Embarcacao::getDataFabricacao).reversed());
                    }
                }
                case 0 ->
                    System.out.println("Saindo...");
                default ->
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    public static Embarcacao criarEmbarcacao(Scanner scanner, List<Embarcacao> embarcacoes, List<Integer> existingIDs) {
        System.out.println("Criar Embarcacao");
        int id = GenerateID.randomUniqueID(1, 1000, existingIDs);

        // Chama as funções secundárias
        String nome = lerNome(scanner, embarcacoes);
        String marca = lerMarca(scanner);
        String modelo = lerModelo(scanner);
        LocalDate dataFabricacao = lerDataFabricacao(scanner);

        int tipoEmbarcacao = lerTipoEmbarcacao(scanner);

        // Pede as informações consoante o tipo de embarcação
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
                int numMotores = lerNumMotores(scanner, 2, 4);
                for (int i = 0; i < numMotores; i++) {
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
            for (Embarcacao e : embarcacoes) {
                if (e.getNome().equals(nome)) {
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

        // Usa-se um hasmap para associar embarcações às suas zonas
        Map<Zona, List<Embarcacao>> embarcacoesPorZona = new HashMap<>();
        for (Zona zona : Zona.values()) {
            embarcacoesPorZona.put(zona, new ArrayList<>());
        }

        for (Embarcacao e : embarcacoes) {
            embarcacoesPorZona.get(e.getZona()).add(e);
        }

        System.out.println("\n--- Embarcacoes disponiveis para remocao ---");
        int totalIndex = 0;
        Map<Integer, Embarcacao> indexMap = new HashMap<>();

        for (Zona zona : Zona.values()) {
            System.out.println("\nZONA " + zona + ":");
            List<Embarcacao> embarcacoesNaZona = embarcacoesPorZona.get(zona);
            if (embarcacoesNaZona.isEmpty()) {
                System.out.println("- SEM REGISTO");
            } else {
                Collections.sort(embarcacoesNaZona, Comparator.comparingInt(Embarcacao::getId));
                for (Embarcacao e : embarcacoesNaZona) {
                    totalIndex++;
                    System.out.printf("%d: ID: %d - %s - %s - %s%n", totalIndex, e.getId(), e.getNome(), e.getMarca(), e.getModelo());
                    indexMap.put(totalIndex, e);
                }
            }
        }

        System.out.print("Escolha o numero da embarcacao que deseja remover: ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        if (indexMap.containsKey(escolha)) {
            Embarcacao removedEmbarcacao = indexMap.get(escolha);
            embarcacoes.remove(removedEmbarcacao);
            System.out.printf("Embarcacao '%s' removida com sucesso.%n", removedEmbarcacao.getNome());
        } else {
            System.out.println("Numero invalido! Nenhuma embarcacao foi removida.");
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
        scanner.nextLine();

        if (index >= 0 && index < marinheiros.size()) {
            Marinheiro removedMarinheiro = marinheiros.remove(index);
            System.out.printf("Marinheiro '%s' removido com sucesso.%n", removedMarinheiro.getNome());
        } else {
            System.out.println("Indice invalido! Nenhum marinheiro foi removido.");
        }
    }

    public static void iniciarMissao(Scanner scanner, Porto porto) {
        if (porto.getEmbarcacoes().isEmpty()) {
            System.out.println("Nao ha embarcacoes disponiveis no porto para iniciar uma missao.");
            return;
        }

        if (porto.getMarinherios().isEmpty()) {
            System.out.println("Nao ha marinheiros disponiveis no porto para iniciar uma missao.");
            return;
        }

        // Exibir embarcações disponíves
        System.out.println("\n--- Embarcacoes disponiveis ---");
        for (int i = 0; i < porto.getEmbarcacoes().size(); i++) {
            Embarcacao embarcacao = porto.getEmbarcacoes().get(i);
            if (!embarcacao.isInMissao()) {
                System.out.printf("%d:" + embarcacao.toString() + "\n", i + 1);
            }
        }

        // Escolher uma embarcação
        System.out.print("\nEscolha o numero da embarcacao para iniciar a missao: ");
        int embarcacaoIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (embarcacaoIndex < 0 || embarcacaoIndex >= porto.getEmbarcacoes().size()) {
            System.out.println("Opção invalida. Opperacao cancelada.");
            return;
        }

        Embarcacao embarcacaoSelecionada = porto.getEmbarcacoes().get(embarcacaoIndex);

        if (embarcacaoSelecionada.isInMissao()) {
            System.out.println("A embarcação ja esta em missao. Escolha outra.");
            return;
        }

        // Exibir marinheiros disponíveis
        ArrayList<Marinheiro> marinheirosDisponiveis = new ArrayList<>();
        System.out.println("\n--- Marinheiros disponíveis ---");
        for (int i = 0; i < porto.getMarinherios().size(); i++) {
            Marinheiro marinheiro = porto.getMarinherios().get(i);
            if (!marinheiro.isInMissao()) {
                marinheirosDisponiveis.add(marinheiro);
                System.out.printf("%d: " + marinheiro.toString() + "\n", i + 1);
            }
        }

        if (marinheirosDisponiveis.isEmpty()) {
            System.out.println("Nao ha marinheiros disponiveis para formar uma tripulacao.");
            return;
        }

        // Selecionar tripulação
        System.out.print("\nEscolha os IDs dos marinheiros para formar a tripulacao (separados por virgula): ");
        ArrayList<Marinheiro> tripulacao = new ArrayList<>();

        while (tripulacao.isEmpty()) {
            System.out.print("\nEscolha os IDs dos marinheiros para formar a tripulacao (separados por virgula): ");
            String[] idsSelecionados = scanner.nextLine().split(",");
            boolean todosValidos = true;

            for (String idStr : idsSelecionados) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Marinheiro marinheiro = null;

                    // Loop para procurar o marinheiro com o ID especificado
                    for (Marinheiro m : marinheirosDisponiveis) {
                        if (m.getId() == id) {
                            marinheiro = m;
                            break;
                        }
                    }

                    if (marinheiro != null) {
                        tripulacao.add(marinheiro);
                    } else {
                        System.out.printf("ID invalido: %d%n", id);
                        todosValidos = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada invalida para ID. Escreva Apenas numeros.");
                    todosValidos = false;
                }
            }

            if (!todosValidos) {
                System.out.println("Um ou mais IDs sao invalidos. Tente novamente.");
                tripulacao.clear(); // Limpa a tripulação para reiniciar o processo
            }
        }

        try {
            // Escolher a zona da missão
            System.out.println("\nZonas disponiveis: NORTE, SUL, ESTE, OESTE");
            System.out.print("Escolha a zona para a missao: ");
            Zona zonaMissao = Zona.valueOf(scanner.nextLine().trim().toUpperCase());

            // Iniciar missão
            porto.lancarMissao(embarcacaoSelecionada, tripulacao, zonaMissao);
            System.out.println("Missão iniciada com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao iniciar missao: " + e.getMessage());
        }
    }

    public static void terminarMissao(Scanner scanner, Porto porto) {
        System.out.println("\n--- Embarcacoes em missao ---");
        for (int i = 0; i < porto.getEmbarcacoes().size(); i++) {
            Embarcacao embarcacao = porto.getEmbarcacoes().get(i);
            if (embarcacao.isInMissao()) {
                System.out.printf("%d:" + embarcacao.getZona() + " -> " + embarcacao.toString() + "\n", i + 1);
            }
        }

        // Escolher uma embarcação
        System.out.print("\nEscolha o numero da embarcacao para terminar missao: ");
        int embarcacaoIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (embarcacaoIndex < 0 || embarcacaoIndex >= porto.getEmbarcacoes().size()) {
            System.out.println("Opcao invalida. Operacao cancelada.");
            return;
        }

        Embarcacao embarcacaoSelecionada = porto.getEmbarcacoes().get(embarcacaoIndex);

        if (embarcacaoSelecionada.isInMissao()) {
            System.out.println("A embarcacao ja esta em missao. Escolhe outra.");
            return;
        }

        if (embarcacaoSelecionada instanceof LanchaRapida) {
            Zona zonaAtual = embarcacaoSelecionada.getZona();
            System.out.println("A embarcacao selecionada e uma LanchaRapida. Todas as lanchas rapidas na zona " + zonaAtual + " terminarao as suas missoes.");

            // Terminar missão de todas as lanchas rápidas na mesma zona
            for (Embarcacao e : porto.getEmbarcacoes()) {
                if (e instanceof LanchaRapida && e.isInMissao() && e.getZona() == zonaAtual) {
                    e.terminarMissao();
                    System.out.println("Missao terminada para: " + e.toString());
                }
            }
        } else {
            // Terminar missão apenas sa embarcação selecionada se não for lancha
            porto.terminarMissao(embarcacaoSelecionada);
            System.out.println("Missão terminada para: " + embarcacaoSelecionada.toString());
        }

        porto.terminarMissao(embarcacaoSelecionada);
    }

    // FILE WRITING
    public static void GuardarInfo(Scanner scanner, List<Marinheiro> marinheiros, List<Embarcacao> embarcacoes) {

        if (marinheiros.isEmpty() && embarcacoes.isEmpty()) {
            System.out.println("Nao ha marinheiros/embarcacoes registradas.");
            return;
        }

        System.out.print("Insira o nome do ficheiro para ler as embarcacoes: ");
        String filePath = scanner.nextLine();

        String informacaoMarinheiros = "";
        String informacaoEmbarcacoes = "";

        for (Marinheiro m : marinheiros) {
            informacaoMarinheiros += m.toString() + "\n";
        }
        for (Embarcacao e : embarcacoes) {
            informacaoEmbarcacoes += e.toString() + "\n";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
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
    public static ArrayList<Marinheiro> LerFicheiroMarinheiros() {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Marinheiro> marinheiros = new ArrayList<>();

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

    public static ArrayList<Embarcacao> LerFicheiroEmbarcacoes() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Embarcacao> embarcacoes = new ArrayList<>();

        System.out.print("Insira o nome do ficheiro para ler as embarcacoes: ");
        String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("BarcoPatrulha[")) {
                    // Ler Barco de Patrulha
                    int motorIndex = line.indexOf(", motor=");
                    String mainInfo = line.substring(0, motorIndex).replace("BarcoPatrulha[", "").replace("]", "").trim();
                    String motorInfo = line.substring(motorIndex + 8).replace("]", "").trim();

                    String[] parts = mainInfo.split(", ");
                    int id = Integer.parseInt(parts[0].split("=")[1]);
                    String nome = parts[1].split("=")[1];
                    String marca = parts[2].split("=")[1];
                    String modelo = parts[3].split("=")[1];

                    String dataFabricacaoRaw = parts[4].split("=")[1].trim();
                    dataFabricacaoRaw = dataFabricacaoRaw.replace("]", "");
                    LocalDate dataFabricacao = LocalDate.parse(dataFabricacaoRaw);

                    Motor motor = parseMotor(motorInfo);
                    BarcoPatrulha barcoPatrulha = new BarcoPatrulha(id, nome, marca, motor, modelo, dataFabricacao);
                    embarcacoes.add(barcoPatrulha);

                } else if (line.startsWith("LanchaRapida[")) {
                    // Ler Lancha Rápida
                    int motoresIndex = line.indexOf(", motores=");
                    String mainInfo = line.substring(0, motoresIndex).replace("LanchaRapida[", "").replace("]", "").trim();
                    String motoresInfo = line.substring(motoresIndex + 10).replace("]", "").trim();

                    String[] parts = mainInfo.split(", ");
                    int id = Integer.parseInt(parts[0].split("=")[1]);
                    String nome = parts[1].split("=")[1];
                    String marca = parts[2].split("=")[1];
                    String modelo = parts[3].split("=")[1];

                    String dataFabricacaoRaw = parts[4].split("=")[1].trim();
                    dataFabricacaoRaw = dataFabricacaoRaw.replace("]", ""); // Remove vírgula
                    LocalDate dataFabricacao = LocalDate.parse(dataFabricacaoRaw);

                    ArrayList<Motor> motores = parseMotores(motoresInfo);
                    LanchaRapida lanchaRapida = new LanchaRapida(id, nome, marca, modelo, dataFabricacao, motores);
                    embarcacoes.add(lanchaRapida);

                } else if (line.startsWith("NavioSuporte[")) {
                    // Ler Navio de Suporte
                    int motoresIndex = line.lastIndexOf(", motores=");
                    String mainInfo = line.substring(0, motoresIndex).replace("NavioSuporte[", "").trim();
                    String motoresInfo = line.substring(motoresIndex + 10).replace("]", "").trim();

                    // Remover o colchete extra do final de mainInfo
                    mainInfo = mainInfo.substring(0, mainInfo.lastIndexOf("]")).trim();

                    String[] parts = mainInfo.split(", ");
                    int id = Integer.parseInt(parts[0].split("=")[1]);
                    String nome = parts[1].split("=")[1];
                    String marca = parts[2].split("=")[1];
                    String modelo = parts[3].split("=")[1];

                    String dataFabricacaoRaw = parts[4].split("=")[1].trim();
                    dataFabricacaoRaw = dataFabricacaoRaw.replace(",", ""); // Remove vírgula
                    LocalDate dataFabricacao = LocalDate.parse(dataFabricacaoRaw);

                    int capacidadeCarga = Integer.parseInt(parts[5].split("=")[1].replace(" kg", ""));
                    int numCamas = Integer.parseInt(parts[6].split("=")[1]);
                    int botes = Integer.parseInt(parts[7].split("=")[1]);

                    ArrayList<Motor> motores = parseMotores(motoresInfo);
                    NavioSuporte navioSuporte = new NavioSuporte(id, nome, marca, modelo, dataFabricacao, motores, capacidadeCarga, numCamas, botes);
                    embarcacoes.add(navioSuporte);
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
        if (motorParts.length != 5) {
            throw new IllegalArgumentException("Formato de motor inválido: " + motorInfo);
        }

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

        // Remover ponto e vírgula no final da string, se existir
        if (motoresInfo.endsWith(";")) {
            motoresInfo = motoresInfo.substring(0, motoresInfo.length() - 1).trim();
        }

        String[] motoresArray = motoresInfo.split("; ");
        for (String motorInfo : motoresArray) {
            if (!motorInfo.isBlank()) {
                motores.add(parseMotor(motorInfo));
            }
        }
        return motores;
    }

    public static void ativarRadarDoPorto(Porto porto) {
        if (porto == null) {
            System.out.println("Porto inválido.");
            return;
        }
        // Ativa o radar e coleta informações
        porto.ativarRadar();
    }
}
