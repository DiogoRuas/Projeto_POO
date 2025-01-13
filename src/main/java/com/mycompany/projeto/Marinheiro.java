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
    

    public Marinheiro(String nome, int id, LocalDate datanascimento, Patente patente) {
        this.nome = nome;
        this.id = id;
        this.dataNascimento = datanascimento;
        this.patente = patente;
    }
    
    public String getNome(){
        return nome;
    }
      
     public int getid(){
        return id;
    }
     
    public void setId(int id) {
        this.id = id;
    }
     
    public Patente getPatente(){
        return this.patente;
    }
     
    public void setPatente (Patente patente) {
         if (patente == Patente.INDEF) {
            throw new IllegalArgumentException("Coloca uma patente valida");
        }
        this.patente = patente;
     }
     
    public LocalDate getdatanascinento () {
         return dataNascimento;
     }
     
    public int getIdade(){
         return Period.between(dataNascimento, LocalDate.now()).getYears();
     }
    
    @Override
    public String toString() {
        return "Marinheiro[" + "id=" + id + ", nome=" + nome + ", idade=" + this.getIdade() + ", patente=" + patente + "]";
    }
}