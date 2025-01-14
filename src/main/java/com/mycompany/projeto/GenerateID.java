/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto;

import java.util.List;
import java.util.Random;

/**
 *
 * @author diogo
 */
public class GenerateID {
    public static int randomID(int min, int max){
        if (min >= max){
            throw new IllegalArgumentException("max tem que ser maior que min");
        }
        
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    
    
    public static int randomUniqueID(int min, int max, List<Integer> numbers) {
        if (min >= max) {
            throw new IllegalArgumentException("max tem que ser maior que min");
        }

        Random r = new Random();
        int generatedNumber;

        // Gera números até encontrar um que não esteja na lista
        do {
            generatedNumber = r.nextInt((max - min) + 1) + min;
        } while (numbers.contains(generatedNumber));

        return generatedNumber;
    }
}
