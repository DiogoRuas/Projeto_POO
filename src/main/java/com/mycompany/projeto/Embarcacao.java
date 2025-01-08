/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto;

import java.time.LocalDate;

/**
 *
 * @author maria
 */
abstract class Embarcacao {
    protected int id;
    protected String nome;
    protected String marca;
    protected String modelo;
    protected String dataFabricacao;
    protected String combustivel;

    public Embarcacao(int id, String nome, String marca, String modelo, String dataFabricacao, String combustivel) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.dataFabricacao = dataFabricacao;
        this.combustivel = combustivel;
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

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public String getCombustivel() {
        return combustivel;
    }

    @Override
    public String toString() {
        return "Embarcacao[" + "id=" + id + ", nome=" + nome + ", marca=" + marca + ", modelo=" + modelo + ", dataFabricacao=" + dataFabricacao + ", combustivel=" + combustivel + "]";
    }
}
