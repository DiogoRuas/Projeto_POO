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
abstract class Embarcacao {
    protected int id;
    protected String nome;
    protected String marca;
    protected String modelo;
    protected LocalDate dataFabricacao;
    protected ArrayList<Motor> motores;
    protected Zona zona;
    protected ArrayList<Marinheiro> tripulacao;

    public Embarcacao(int id, String nome, String marca, String modelo, LocalDate dataFabricacao) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.dataFabricacao = dataFabricacao;
        this.motores = new ArrayList<>();
        this.zona = Zona.INDEF;
        this.tripulacao = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setMotores(ArrayList<Motor> motores) {
        this.motores = motores;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public ArrayList<Motor> getMotores() {
        return motores;
    }

    public Zona getZona() {
        return zona;
    }

    public ArrayList<Marinheiro> getTripulacao() {
        return tripulacao;
    }
    
    @Override
    public String toString() {
        return "Embarcacao[" + "id=" + id + ", nome=" + nome + ", marca=" + marca + ", modelo=" + modelo + ", dataFabricacao=" + dataFabricacao + "]";
    }
}
