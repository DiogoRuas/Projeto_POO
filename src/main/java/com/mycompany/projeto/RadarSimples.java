/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto;

/**
 *
 * @author diogo
 */

import java.util.ArrayList;

public class RadarSimples implements Radar {
    
    private boolean isOn;
    private ArrayList<Embarcacao> embarcacoesDetectadas;

    public RadarSimples() {
        this.isOn = false;
        this.embarcacoesDetectadas = new ArrayList<>();
    }

    @Override
    public void ligar() {
        this.isOn = true;
        System.out.println("Radar ligado.");
    }

    @Override
    public void desligar() {
        this.isOn = false;
        System.out.println("Radar desligado.");
    }

    @Override
    public boolean isOn() {
        return this.isOn;
    }
    
    @Override
    public ArrayList<Embarcacao> detectarEmbarcacoes(ArrayList<Embarcacao> todasEmbarcacoes, Zona zona) {
        if (!isOn) {
            System.out.println("Radar esta desligado. Nenhuma informação disponivel.");
            return null;
        }
        
        embarcacoesDetectadas.clear();
        for (Embarcacao e : todasEmbarcacoes) {
            if (zona.equals(e.getZona())) {
                embarcacoesDetectadas.add(e);
            }
        }
        System.out.println("Embarcacoes detectadas na zona " + zona + ": " + embarcacoesDetectadas.size());
        return embarcacoesDetectadas;
    }
    
    @Override
    public void exibirInformacoesDeteccoes() {
        System.out.println("Embarcacoes detectadas:");
        for (Embarcacao e : embarcacoesDetectadas) {
            System.out.println(e.toString());
        }
    }
}
