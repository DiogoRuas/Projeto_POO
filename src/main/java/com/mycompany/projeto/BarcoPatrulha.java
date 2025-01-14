/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto;

import java.time.LocalDate;
/**
 *
 * @author maria
 */
class BarcoPatrulha extends Embarcacao {
    
    private Motor motor;
    private boolean holofote;
    private Radar radar;
    
    public BarcoPatrulha(int id, String nome, String marca, String modelo, LocalDate dataFabricacao) {
        super(id, nome, marca, modelo, dataFabricacao);
    }
}
