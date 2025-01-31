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
    
    private ArrayList<Motor> motores;
    private boolean holofote;
    
    public LanchaRapida(int id, String nome, String marca, String modelo, LocalDate dataFabricacao, ArrayList <Motor> motores) {
        super(id, nome, marca, modelo, dataFabricacao);
        
        if (motores.size() < 2 || motores.size() > 4){
            throw new IllegalArgumentException("Lacha Rapida: Quantidade invalida de motores. Tem de ter 2 a 4");
        }

        this.motores = motores;
        this.holofote = false;
    }
    
    @Override
    public void ativarMissao(Zona zona, ArrayList<Marinheiro> tripulacao){
        
        if (this.inMissao == true){
            throw new IllegalArgumentException("Lancha Rápida: Ja esta em missao");
        }
        
        if (tripulacao.size() < 2 || tripulacao.size() > 4){
            throw new IllegalArgumentException("Lacha Rapida: Quantidade invalida de marinheiros. Tem de ter 2 a 4");
        }
        
        boolean hasSargento = false;
        for (Marinheiro m : tripulacao) {
            if (m.getPatente() == Patente.SARGENTO) {
                hasSargento = true;
                break;
            }
        }

        if (!hasSargento) {
            throw new IllegalArgumentException("Lancha Rápida: Deve haver pelo menos um sargento na tripulação.");
        }
        
        
        for (Marinheiro m : this.tripulacao) {
            m.setInMissao(true);
        }
        
        this.inMissao = true;
        this.zona = zona;
        this.holofote = true;
        this.tripulacao = tripulacao;
        
        System.out.println("Lancha rapida " + "'" + this.nome + "': Estou em missao de captura");
    }
    
    @Override
    public void terminarMissao(){
        
        if (this.inMissao == false){
            throw new IllegalArgumentException("Lancha Rápida: Nao esta em missao");
        }
        
        this.inMissao = false;
        this.zona = Zona.INDEF;
        this.holofote = false;
        
        for (Marinheiro m : this.tripulacao) {
            m.setInMissao(false);
        }
        
        System.out.println("Lancha rapida " + "'" + this.nome + "': Terminei a missao de captura");
    }
    
    @Override
    public String toString() {
        String motoresInfo = "";
        for (Motor m : motores) {
            motoresInfo += m.toString()+ "; ";
        }
        return "LanchaRapida[" + super.toString() + "]" +  ", motores=" + motoresInfo + "]";
    }
}
