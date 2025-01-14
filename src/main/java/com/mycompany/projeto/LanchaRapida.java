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
    
    public LanchaRapida(int id, String nome, String marca, String modelo, LocalDate dataFabricacao, ArrayList <Motor> motores, ArrayList <Marinheiro> tripulacao) {
        super(id, nome, marca, modelo, dataFabricacao);
        
        if (motores.size() < 2 || motores.size() > 4){
            throw new IllegalArgumentException("Lacha Rapida: Quantidade invalida de motores para. Tem de ter 2 a 4");
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

        this.motores = motores;
        this.holofote = false;
    }
    
    @Override
    public void ativarMissao(Zona zona){
        this.inMissao = true;
        this.zona = zona;
        this.holofote = true;
        System.out.println("Lancha rapida " + "'" + this.nome + "': Estou em missao de captura");
    }
    
    @Override
    public void terminarMissao(){
        this.inMissao = false;
        this.zona = Zona.INDEF;
        this.holofote = false;
        System.out.println("Lancha rapida " + "'" + this.nome + "': Terminei a missao de captura");
    }
    
}
