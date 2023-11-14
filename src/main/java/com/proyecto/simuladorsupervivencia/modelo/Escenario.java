/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.simuladorsupervivencia.modelo;

/**
 *
 * @author q-ql
 */
public class Escenario {
    private String descripcion;
    private String[] opciones;
    private String rutaImagen;

    public Escenario(String descripcion, String[] opciones, String rutaImagen) {
        this.descripcion = descripcion;
        this.opciones = opciones;
        this.rutaImagen = rutaImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    
    public String getRutaImagen() {
        return rutaImagen;
    }

    public String[] getOpciones() {
        return opciones;
    }
}

