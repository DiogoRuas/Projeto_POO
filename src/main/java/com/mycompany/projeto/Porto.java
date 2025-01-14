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
    
    private ArrayList <Embarcacao> EmbarcacoesAtracadas;
    private String nome;
    private int numTotalMissoes;
    private RadarSimples radar;
    private Zona zonaAssociada;

    public Porto(String nome) {
        this.EmbarcacoesAtracadas = new ArrayList <Embarcacao>();
        this.nome = nome;
        this.numTotalMissoes = 0;
        this.radar = new RadarSimples();
        this.zonaAssociada = Zona.ESTE;
    }

    public String getNome() {
        return nome;
    }

    public int getNumTotalMissoes() {
        return numTotalMissoes;
    }
    
    public void showListaDeEmbarcacoes(){
        for (Embarcacao embarcacao: EmbarcacoesAtracadas){
            embarcacao.toString();
        }
    }
    
    public void lancarMissao(Embarcacao embarcacao, ArrayList<Marinheiro> tripulacao, Zona zona){
        if(!this.EmbarcacoesAtracadas.contains(embarcacao)){
            throw new IllegalStateException("Porto: A embarcacao nao esta atracada");
        }
        embarcacao.ativarMissao(zona, tripulacao);
        this.numTotalMissoes += 1;
    }
    
    public void terminarMissao(Embarcacao embarcacao){
        if(this.EmbarcacoesAtracadas.contains(embarcacao)){
            throw new IllegalStateException("Porto: A embarcacao esta atracada");
        }
        embarcacao.terminarMissao();
    }
    
    public void ativarRadar(ArrayList<Embarcacao> todasEmbarcacoes) {
        if (this.radar == null) {
            throw new IllegalStateException("Navio Suporte: Radar não está instalado.");
        }

        this.radar.ligar();
        
        ArrayList<Embarcacao> detectadasNoPorto = radar.detectarEmbarcacoes(todasEmbarcacoes, Zona.INDEF);
        ArrayList<Embarcacao> detectadasNaZonaAssociada = radar.detectarEmbarcacoes(todasEmbarcacoes, this.zonaAssociada);
        this.radar.exibirInformacoesDeteccoes();
    }
}
