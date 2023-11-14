/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.proyecto.simuladorsupervivencia;

/**
 *
 * @author q-ql
 */
/**
 TODO:
 *Al finalizar la partida mostrar un OptionPane preguntando al jugador si
 desea ver sus estadisticas(puntos de decision) en un JTable, caso contrario cerrar el programa
 *A;adir sistema de items que el jugador use y recolecte en los escenarios
 * Imagenes acorde a los escenarios
 * Transiciones
 */
import com.proyecto.simuladorsupervivencia.controlador.ControladorJuego;
import com.proyecto.simuladorsupervivencia.modelo.LogicaJuego;
import com.proyecto.simuladorsupervivencia.vista.VistaJuego;


public class SimuladorSupervivencia {

    public static void main(String[] args) {
        LogicaJuego logicaJuego = new LogicaJuego();
        VistaJuego vista = new VistaJuego();
        ControladorJuego controlador = new ControladorJuego(vista, logicaJuego);
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    }

}

