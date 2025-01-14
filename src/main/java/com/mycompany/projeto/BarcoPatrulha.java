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
class BarcoPatrulha extends Embarcacao {
    
    private Motor motor;
    private boolean holofote;
    private Radar radar;
    
    public BarcoPatrulha(int id, String nome, String marca, String modelo, LocalDate dataFabricacao) {
        super(id, nome, marca, modelo, dataFabricacao);
    }
    
    @Override
    public void ativarMissao(Zona zona, ArrayList<Marinheiro> tripulacao){
        
        if (this.inMissao == true){
            throw new IllegalArgumentException("Lancha Rápida: Ja esta em missao");
        }
        
        if (tripulacao.size() < 2 || tripulacao.size() > 4){
            throw new IllegalArgumentException("Lacha Rapida: Quantidade invalida de motores para. Tem de ter 2 a 4");
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
}
