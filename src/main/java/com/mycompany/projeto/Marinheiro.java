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
public class Marinheiro {
    private String nome;
    private int id;
    private LocalDate datanascimento;
    private Patente patente;
    

    public Marinheiro(String nome, int id, LocalDate datanascimento, Patente patente) {
        this.nome = nome;
        this.id = id;
        this.datanascimento = datanascimento;
        this.patente = patente;
    }
    
    public String getNome(){
        return nome;
    }
    
      public final void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome nao pode ser nulo ou vazio");
        }
        this.nome = nome;
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
     
     public final void setPatente (Patente patente) {
         if (patente == Patente.INDEF) {
            throw new IllegalArgumentException("Coloca uma patente valida");
        }
        this.patente = patente;
     }
     
     public LocalDate getdatanascinento () {
         return datanascimento;
     }
     
     public final void setdatanascimento(LocalDate datanascimento) {
         if (datanascimento == null) {
            throw new IllegalArgumentException("A data de nascimento nao pode ser nula");
        }
        this.datanascimento = datanascimento;
     }
}