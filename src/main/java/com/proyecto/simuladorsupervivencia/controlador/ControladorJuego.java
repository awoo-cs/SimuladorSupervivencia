/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.simuladorsupervivencia.controlador;

/**
 *
 * @author q-ql
 */
import com.proyecto.simuladorsupervivencia.modelo.Escenario;
import com.proyecto.simuladorsupervivencia.modelo.LogicaJuego;
import com.proyecto.simuladorsupervivencia.vista.VistaJuego;
import javax.swing.*;
import java.awt.*;


public class ControladorJuego {
    private VistaJuego vista;
    private LogicaJuego logicaJuego;

    public ControladorJuego(VistaJuego vista, LogicaJuego logicaJuego) {
        this.vista = vista;
        this.logicaJuego = logicaJuego;

        this.vista.getBtnIniciarJuego().addActionListener(e -> iniciarJuego());

        this.vista.getBtnSiguiente().addActionListener(e -> {
            String opcionSeleccionada = vista.getOpcionSeleccionada();
            if (opcionSeleccionada == null) {
                JOptionPane.showMessageDialog(vista, "Por favor, selecciona una opcion antes de continuar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            logicaJuego.procesarDecision(opcionSeleccionada);
            avanzarAlSiguienteEscenario();
            actualizarEstadoJugador();
        });
    }

    private void iniciarJuego() {
        vista.getBtnIniciarJuego().setVisible(false);
        vista.getLblInstrucciones().setVisible(false);
        vista.getBtnSiguiente().setVisible(true);
        vista.getLblEscenario().setVisible(true);
        
        // Move the health and energy bars to the top of the window
        vista.moveToTop();
        
        logicaJuego.reiniciarJuego();
        Escenario escenarioInicial = logicaJuego.getEscenarioActual();
        if (escenarioInicial != null) {
            vista.setEscenarioTexto(wrapText(escenarioInicial.getDescripcion(), 50));
            vista.setOpciones(escenarioInicial.getOpciones());
        String rutaImagen = escenarioInicial.getRutaImagen();
        vista.setImagenEscenario(rutaImagen);

        }

        logicaJuego.setEnergia(100);
        logicaJuego.setSalud(100);

        vista.getBarraSalud().setValue(100);
        vista.getLblSalud().setText("Salud: 100/100");
        vista.getBarraEnergia().setValue(100);
        vista.getLblEnergia().setText("Energia: 100/100");
    }

    private void avanzarAlSiguienteEscenario() {
        logicaJuego.siguienteEscenario();
        Escenario escenario = logicaJuego.getEscenarioActual();
        if (escenario != null) {
            vista.setEscenarioTexto(wrapText(escenario.getDescripcion(), 50));
            vista.setOpciones(escenario.getOpciones());
            vista.setImagenEscenario(escenario.getRutaImagen());
        } else {
            mostrarFinDelJuego();
        }
        actualizarEstadoJugador();
    }

    private String wrapText(String text, int length) {
        String[] words = text.split(" ");
        StringBuilder wrapped = new StringBuilder();
        StringBuilder line = new StringBuilder(words[0]);

        for (int i = 1; i < words.length; i++) {
            if (line.length() + words[i].length() + 1 > length) {
                wrapped.append(line).append(" ");
                line = new StringBuilder(words[i]);
            } else {
                line.append(" ").append(words[i]);
            }
        }
        wrapped.append(line);

        return wrapped.toString();
    }

    private void mostrarFinDelJuego() {
        int opcion = JOptionPane.showOptionDialog(
            vista,
            "Fin del Juego",
            "Has llegado al final",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new String[]{"Reiniciar", "Salir"},
            null
        );
        if (opcion == JOptionPane.YES_OPTION) {
            iniciarJuego();
        } else {
            System.exit(0);
        }
    }

    public void actualizarEstadoJugador() {
        int saludActual = logicaJuego.getSalud();
        vista.getBarraSalud().setValue(saludActual);
        vista.getLblSalud().setText("Salud: " + saludActual + "/100");

        int energiaActual = logicaJuego.getEnergia();
        vista.getBarraEnergia().setValue(energiaActual);
        vista.getLblEnergia().setText("Energia: " + energiaActual + "/100");

        if (saludActual <= 30) {
            vista.getBarraSalud().setForeground(Color.RED);
        } else if (saludActual <= 60) {
            vista.getBarraSalud().setForeground(Color.YELLOW);
        } else{
            vista.getBarraSalud().setForeground(Color.GREEN);
        }

        if (energiaActual <= 30) {
            vista.getBarraEnergia().setForeground(Color.RED);
        } else if (energiaActual <= 60) {
            vista.getBarraEnergia().setForeground(Color.YELLOW);
        } else{
            vista.getBarraEnergia().setForeground(Color.GREEN);
        }

        /*vista.getBarraEnergia().setValue(logicaJuego.getEnergia());
        vista.getLblEnergia().setText("Energia: " + logicaJuego.getEnergia() + "/100");
        vista.getBarraSalud().setValue(logicaJuego.getSalud());
        vista.getLblSalud().setText("Salud: " + logicaJuego.getSalud() + "/100");
        vista.getBarraEnergia().setValue(logicaJuego.getEnergia());
        vista.getLblEnergia().setText("Energia: " + logicaJuego.getEnergia() + "/100");*/
    }

}

