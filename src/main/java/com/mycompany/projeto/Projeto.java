/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 *
 * @author diogo
 */
public class Projeto {

    public static void main(String[] args) {
        
        
        Marinheiro joao = new Marinheiro("Joao", 123456789, LocalDate.of(1980, Month.MARCH, 14), Patente.OFICIAL);
        Marinheiro maria = new Marinheiro("Maria", 987654321, LocalDate.of(1990, Month.APRIL, 10), Patente.SARGENTO);
        Marinheiro pedro = new Marinheiro("Pedro", 567891234, LocalDate.of(1975, Month.JUNE, 5), Patente.PRACA);
        Marinheiro ana = new Marinheiro("Ana", 678912345, LocalDate.of(1985, Month.FEBRUARY, 20), Patente.SARGENTO);     
        
        Motor motor1 = new Motor(30000, 2000, 50, "Gasolina", 12);
        Motor motor2 = new Motor(20000, 3000, 60, "Diesel", 15);
        Motor motor3 = new Motor(25000, 1500, 40, "Etanol", 10);
        Motor motor4 = new Motor(25000, 4000, 80, "GNV", 8);
        Motor motor5 = new Motor(28000, 2500, 45, "Gasolina", 14);
        Motor motor6 = new Motor(27000, 3500, 70, "Diesel", 16);
        
        ArrayList<Motor> motores1 = new ArrayList<>();
        motores1.add(motor1);
        motores1.add(motor2);
        
        ArrayList<Motor> motores2 = new ArrayList<>();
        motores2.add(motor3);
        motores2.add(motor4);
        
        ArrayList<Motor> motores3 = new ArrayList<>();
        motores3.add(motor5);
        motores3.add(motor6);
        
        
        ArrayList<Marinheiro> tripulacao1 = new ArrayList<>();
        tripulacao1.add(joao);
        tripulacao1.add(maria);

        ArrayList<Marinheiro> tripulacao2 = new ArrayList<>();
        tripulacao2.add(pedro);
        tripulacao2.add(ana);
        
        
        ArrayList<Embarcacao> listaEmbarcacoes = new ArrayList<>();
        
        LanchaRapida lancha1 = new LanchaRapida(77777, "Lancha1", "Toyota", "Model 5", LocalDate.of(1999, Month.SEPTEMBER, 11), motores1);
        listaEmbarcacoes.add(lancha1);
        LanchaRapida lancha2 = new LanchaRapida(88888, "Lancha2", "Yamaha", "Model Z", LocalDate.of(2005, Month.JUNE, 18), motores2);
        listaEmbarcacoes.add(lancha2);
        
        NavioSuporte navioSuporte1 = new NavioSuporte(112, "Suporte1", "dopsaj", "Model X", LocalDate.of(1980, Month.SEPTEMBER, 22), motores2, 10000, 100, 8);
        listaEmbarcacoes.add(navioSuporte1);
        NavioSuporte navioSuporte2 = new NavioSuporte(113, "Suporte2", "NavalTech", "Model Y", LocalDate.of(1995, Month.MAY, 15), motores3, 20000, 150, 12);
        listaEmbarcacoes.add(navioSuporte2);
        
        lancha1.ativarMissao(Zona.NORTE, tripulacao1);
        lancha2.ativarMissao(Zona.NORTE, tripulacao2);
        navioSuporte1.ativarMissao(Zona.NORTE, tripulacao1);
        navioSuporte1.ativarRadar(listaEmbarcacoes);
    }
}
