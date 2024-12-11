/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto;

/**
 *
 * @author maria
 */
public class Motor {

    private int potencia;
    private double cilindrada;
    private double fuelTankCapacity;
    private String combustivel;
    private int consumo;
    
    
    public int getPotencia() {
        return potencia;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public double getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public String getCombustivel() {
        return combustivel;
    }
    
    public int getConsumo(){
        return consumo;
    }
    

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public void setFuelTankCapacity(double fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }
    
    
    

}
