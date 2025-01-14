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
    protected Zona zona;
    protected ArrayList<Marinheiro> tripulacao;
    protected boolean inMissao;

    public Embarcacao(int id, String nome, String marca, String modelo, LocalDate dataFabricacao) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da embarcacao nao pode ser nulo ou vazio.");
        }
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.dataFabricacao = dataFabricacao;
        this.zona = Zona.INDEF;
        this.tripulacao = new ArrayList<>();
        this.inMissao = false;
    }

    
    // GETTERS
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

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public Zona getZona() {
        return zona;
    }

    public ArrayList<Marinheiro> getTripulacao() {
        return tripulacao;
    }

    public boolean isInMissao() {
        return inMissao;
    }
    
    
    
    
    // SETTERS

    public void setZona(Zona zona) {
        this.zona = zona;
    }
    
    
    
    // METODOS
    public abstract void ativarMissao();
    
    public abstract void terminarMissao();
      
    @Override
    public String toString() {
        return "Embarcacao[" + "id=" + id + ", nome=" + nome + ", marca=" + marca + ", modelo=" + modelo + ", dataFabricacao=" + dataFabricacao + "]";
    }
}
