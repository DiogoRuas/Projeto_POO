/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto;

import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class Porto {
    
    private ArrayList <Embarcacao> embarcacoes;
    private String nome;
    private int numTotalMissoes;
    private RadarSimples radar;
    private ArrayList<Embarcacao> embarcacoesDetetadas;

    public Porto(String nome) {
        this.embarcacoes = new ArrayList <Embarcacao>();
        this.nome = nome;
        this.numTotalMissoes = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getNumTotalMissoes() {
        return numTotalMissoes;
    }
    
    public void showListaDeEmbarcacoes(){
        for (Embarcacao embarcacao: embarcacoes){
            embarcacao.toString();
        }
    }

}
