/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto;
 

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author maria
 */
public class Marinheiro {
    
    private String nome;
    private int id;
    private LocalDate dataNascimento;
    private Patente patente;
    private boolean inMissao;
    

    public Marinheiro(String nome, int id, LocalDate datanascimento, Patente patente) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da embarcacao nao pode ser nulo ou vazio.");
        }
        if (patente == Patente.INDEF) {
            throw new IllegalArgumentException("Coloca uma patente valida");
        }
        if (this.getIdade() < 35 && patente == Patente.OFICIAL){
            throw new IllegalArgumentException("Nao pode haver oficiais com menos de 35 anos");
        }
        
        this.nome = nome;
        this.id = id;
        this.dataNascimento = datanascimento;
        this.patente = patente;
        this.inMissao = false;
    }
    
    // GETTERS
    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public Patente getPatente() {
        return this.patente;
    }

    public LocalDate getdataNascinento() {
        return dataNascimento;
    }

    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public boolean isInMissao() {
        return inMissao;
    }
    
    
    // SETTERS
    public void setId(int id) {
        this.id = id;
    }
    
    public void setPatente(Patente patente) {
        if (patente == Patente.INDEF) {
            throw new IllegalArgumentException("Coloca uma patente valida");
        }
        if (this.getIdade() < 35 && patente == Patente.OFICIAL){
            throw new IllegalArgumentException("Nao pode haver oficiais com menos de 35 anos");
        }
        this.patente = patente;
    }

    public void setInMissao(boolean inMissao) {
        this.inMissao = inMissao;
    }
    
    
    // METODOS
    @Override
    public String toString() {
        return "Marinheiro[" + "id=" + id + ", nome=" + nome + ", idade=" + this.getIdade() + ", patente=" + patente + ", inMissao=" + inMissao + "]";
    }
}