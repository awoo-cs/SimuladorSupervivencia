/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.simuladorsupervivencia.modelo;

/**
 *
 * @author q-ql
 */
public class DecisionJugador {
    private String decision;
    private String descripcion;
    private int puntos;

    public DecisionJugador(String decision, String descripcion, int puntos) {
        this.decision = decision;
        this.descripcion = descripcion;
        this.puntos = puntos;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
