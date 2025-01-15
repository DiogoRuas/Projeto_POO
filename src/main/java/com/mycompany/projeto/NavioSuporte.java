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
class NavioSuporte extends Embarcacao {
    
    private ArrayList<Motor> motor;
    private boolean holofote;
    private RadarSimples radar;
    private int capacidadeCarga;
    private int numCamas;
    private int botesSalvaVidas;
    
    
    public NavioSuporte(int id, String nome, String marca, String modelo, LocalDate dataFabricacao, ArrayList<Motor> motores, int capacidadeCarga, int numCamas, int botes) {
        super(id, nome, marca, modelo, dataFabricacao);
        
        if (motores.size() != 2){
            throw new IllegalArgumentException("Navio Suporte: Quantidade invalida de motores. Tem de ter 2 motores");
        }
        
        for (Motor m : motores){
            if(m.getPotencia() < 25000){
                throw new IllegalArgumentException("Navio Suporte: Todos os motores devem ter mais de 25000cv de potencia");
            }
        }

        this.motor = motores;
        this.holofote = false;
        this.capacidadeCarga = capacidadeCarga;
        this.numCamas = numCamas;
        this.botesSalvaVidas = botes;
        this.radar = new RadarSimples();
    }
    
    @Override
    public void ativarMissao(Zona zona, ArrayList<Marinheiro> tripulacao){
        
        if (this.inMissao == true){
            throw new IllegalArgumentException("Navio Suporte: Ja esta em missao");
        }
        
        if (tripulacao.size() < 4 || tripulacao.size() > 10) {
            throw new IllegalArgumentException("Navio Suporte: Quantidade invalida de motores para. Tem de ter 4 a 10");
        }
        
        boolean hasSargento = false;
        boolean hasOficial = false;

        for (Marinheiro m : tripulacao) {
            if (m.getPatente() == Patente.SARGENTO) {
                hasSargento = true;
            }
            if (m.getPatente() == Patente.OFICIAL) {
                hasOficial = true;
            }
        }

        if (!hasSargento) {
            throw new IllegalArgumentException("Navio Suporte: Deve haver pelo menos um sargento na tripulação.");
        }

        if (!hasOficial) {
            throw new IllegalArgumentException("Navio Suporte: Deve haver pelo menos um oficial na tripulação.");
        }
        
        for (Marinheiro m : this.tripulacao) {
            m.setInMissao(true);
        }
        this.inMissao = true;
        this.zona = zona;
        this.holofote = true; 
        this.tripulacao = tripulacao;
        
        System.out.println("Navio Suporte " + "'" + this.nome + "': Estou em missao de Suporte");
    }
    
    @Override
    public void terminarMissao(){
        
        if (this.inMissao == false){
            throw new IllegalArgumentException("Navio Suporte: Nao esta em missao");
        }
        
        this.inMissao = false;
        this.zona = Zona.INDEF;
        this.holofote = false;
        for (Marinheiro m : this.tripulacao) {
            m.setInMissao(false);
        }
        
        System.out.println("Navio Suporte " + "'" + this.nome + "': Terminei a missao de Suporte");
    }
    
    public void ativarRadar(ArrayList<Embarcacao> todasEmbarcacoes) {
        if (!this.inMissao) {
            throw new IllegalStateException("Navio Suporte: O radar só pode ser ativado durante uma missão.");
        }
        if (this.radar == null) {
            throw new IllegalStateException("Navio Suporte: Radar não está instalado.");
        }

        this.radar.ligar();
        ArrayList<Embarcacao> detectadas = radar.detectarEmbarcacoes(todasEmbarcacoes, this.zona);
        this.radar.exibirInformacoesDeteccoes();
    }
    
    @Override
    public String toString() {
        String motoresInfo = "";
        for (Motor m : motor) {
            motoresInfo += m.toString() + "; ";
        }
        return "NavioSuporte[" + super.toString()
                + ", capacidadeCarga=" + capacidadeCarga + " kg"
                + ", numCamas=" + numCamas
                + ", botesSalvaVidas=" + botesSalvaVidas + "]"
                + ", motores=" + motoresInfo;
    }
}
