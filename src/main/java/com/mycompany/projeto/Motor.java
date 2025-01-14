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
    private int potencia; // Potência em cavalos (cv)
    private double cilindrada; // Cilindrada em cm³
    private double fuelTankCapacity; // Capacidade do tanque
    private String combustivel; // Tipo de combustível
    private int consumo; // Consumo em litros por hora

    public Motor(int potencia, double cilindrada, double fuelTankCapacity, String combustivel, int consumo) {
        this.potencia = potencia;
        this.cilindrada = cilindrada;
        this.fuelTankCapacity = fuelTankCapacity;
        this.combustivel = combustivel;
        this.consumo = consumo;
    }

    // GETTERS
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

    public int getConsumo() {
        return consumo;
    }

    // SETTERS
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

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    // METODOS
    @Override
    public String toString() {
        return "Motor {" +
                "Potência=" + potencia + " cv" +
                ", Cilindrada=" + cilindrada + " cm³" +
                ", Capacidade do Tanque=" + fuelTankCapacity + " litros" +
                ", Combustível='" + combustivel + '\'' +
                ", Consumo=" + consumo + " L/h" +
                '}';
    }
}
