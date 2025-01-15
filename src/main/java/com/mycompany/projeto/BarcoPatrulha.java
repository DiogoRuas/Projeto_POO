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
    private RadarSimples radar;

    public BarcoPatrulha(int id, String nome, String marca, String modelo, LocalDate dataFabricacao) {
        super(id, nome, marca, modelo, dataFabricacao);
    }

    @Override
    public void ativarMissao(Zona zona, ArrayList<Marinheiro> tripulacao) {

        if (this.inMissao == true) {
            throw new IllegalArgumentException("Barco de Patrulha: Ja esta em missao");
        }

        if (tripulacao.size() < 2 || tripulacao.size() > 4) {
            throw new IllegalArgumentException("Barco de Patrulha: Quantidade invalida de motores para. Tem de ter 2 a 4");
        }

        boolean hasOficial = false;
        int contadorOficiais = 0;

        for (Marinheiro m : tripulacao) {
            if (m.getPatente() == Patente.OFICIAL) {
                contadorOficiais++;
            }
        }

        if (contadorOficiais != 1) {
            throw new IllegalArgumentException("Barco de Patrulha: Deve haver exatamente um oficial na tripulação.");
        }

        for (Marinheiro m : tripulacao) {
            m.setInMissao(true);
        }

        this.inMissao = true;
        this.zona = zona;
        this.holofote = true;
        this.tripulacao = tripulacao;
        this.radar = new RadarSimples();

        System.out.println("Barco de Patrulha " + "'" + this.nome + "': Estou em missão de captura");
    }

    @Override
    public void terminarMissao() {

        if (!this.inMissao) {
            throw new IllegalArgumentException("Barco de patrulha: Não está em missão.");
        }

        this.inMissao = false;
        this.zona = Zona.INDEF;
        this.holofote = false;

        for (Marinheiro m : this.tripulacao) {
            m.setInMissao(false);
        }

        System.out.println("Barco de Patrulha " + "'" + this.nome + "': Terminei a missão de captura");
    }

    public void ativarRadar(ArrayList<Embarcacao> todasEmbarcacoes) {
        if (!this.inMissao) {
            throw new IllegalStateException("Barco de Patrulha: O radar só pode ser ativado durante uma missão.");
        }
        if (this.radar == null) {
            throw new IllegalStateException("Barco de Patrulha: Radar não está instalado.");
        }

        this.radar.ligar();
        ArrayList<Embarcacao> detectadas = radar.detectarEmbarcacoes(todasEmbarcacoes, this.zona);
        this.radar.exibirInformacoesDeteccoes();
    }

    @Override
    public String toString() {
        return "BarcoPatrulha[" + super.toString() + "]";
    }

}
