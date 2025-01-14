/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
class LanchaRapida extends Embarcacao {
    
    private ArrayList<Motor> motor;
    private boolean holofote;
    
    public LanchaRapida(int id, String nome, String marca, String modelo, LocalDate dataFabricacao) {
        super(id, nome, marca, modelo, dataFabricacao);
    }
}
