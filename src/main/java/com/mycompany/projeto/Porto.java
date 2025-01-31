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

    private ArrayList<Embarcacao> embarcacoes;
    private ArrayList<Marinheiro> marinheiros;
    private String nome;
    private int numTotalMissoes;
    private RadarSimples radar;
    private Zona zonaAssociada;

    public Porto(String nome) {
        this.embarcacoes = new ArrayList<>();
        this.marinheiros = new ArrayList<>();
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

    public ArrayList<Embarcacao> getEmbarcacoes() {
        return embarcacoes;
    }

    public ArrayList<Marinheiro> getMarinherios() {
        return marinheiros;
    }

    public Zona getZonaAssociada() {
        return zonaAssociada;
    }

    public void showListaDeEmbarcacoes() {
        for (Embarcacao embarcacao : embarcacoes) {
            embarcacao.toString();
        }
    }

    public void setEmbarcacoes(ArrayList<Embarcacao> embarcacoes) {
        this.embarcacoes = embarcacoes;
    }

    public void setMarinherios(ArrayList<Marinheiro> marinherios) {
        this.marinheiros = marinherios;
    }

    public void adicionarEmbarcacao(Embarcacao embarcacao) {
        if (embarcacao == null) {
            throw new IllegalStateException("Porto: A embarcacao nao pode ter valor null");
        }
        this.embarcacoes.add(embarcacao);
    }

    public void adicionarMarinheiro(Marinheiro marinheiro) {
        if (marinheiro == null) {
            throw new IllegalStateException("Porto: A embarcacao nao pode ter valor null");
        }
        this.marinheiros.add(marinheiro);
    }

    public void lancarMissao(Embarcacao embarcacao, ArrayList<Marinheiro> tripulacao, Zona zona) {
        if (!this.embarcacoes.contains(embarcacao)) {
            throw new IllegalStateException("Porto: A embarcacao esta em missao");
        }
        embarcacao.ativarMissao(zona, tripulacao);
        this.numTotalMissoes += 1;
    }

    public void terminarMissao(Embarcacao embarcacao) {
        if (this.embarcacoes.contains(embarcacao)) {
            throw new IllegalStateException("Porto: A embarcacao esta atracada");
        }
        embarcacao.terminarMissao();
    }

    public void ativarRadar() {
        if (this.radar == null) {
            throw new IllegalStateException("Navio Suporte: Radar não está instalado.");
        }

        this.radar.ligar();

        ArrayList<Embarcacao> detectadasNoPorto = radar.detectarEmbarcacoes(this.embarcacoes, Zona.INDEF);
        this.radar.exibirInformacoesDeteccoes();
        ArrayList<Embarcacao> detectadasNaZonaAssociada = radar.detectarEmbarcacoes(this.embarcacoes, this.zonaAssociada);
        this.radar.exibirInformacoesDeteccoes();
    }
}
