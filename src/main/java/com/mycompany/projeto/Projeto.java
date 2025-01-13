/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class Projeto {

    public static void main(String[] args) {     
        Marinheiro joao = new Marinheiro("Joao", 123456789, LocalDate.of(1980, Month.MARCH, 14), Patente.OFICIAL);
        System.out.println(joao.toString());
    }
}
