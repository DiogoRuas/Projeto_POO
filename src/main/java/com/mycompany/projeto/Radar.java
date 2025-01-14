/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.projeto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maria
 */
public interface Radar {
    void ligar();

    void desligar();

    boolean isOn();

    List<Embarcacao> detectarEmbarcacoes(ArrayList<Embarcacao> todasEmbarcacoes, Zona zona);

    void exibirInformacoesDeteccoes();
}
