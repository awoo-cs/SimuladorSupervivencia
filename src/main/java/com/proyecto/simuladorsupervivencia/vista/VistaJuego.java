/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.simuladorsupervivencia.vista;

/**
 *
 * @author q-ql
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaJuego extends JFrame {
    private JLabel lblInstrucciones;
    private JLabel lblSalud;
    private JLabel lblEnergia;
    private JProgressBar barraSalud;
    private JProgressBar barraEnergia;
    private JButton btnIniciarJuego;
    private JButton btnSiguiente;
    private JLabel lblEscenario;
    private JPanel panelOpciones;
    private JPanel panelTop;
    private JLabel lblImagenEscenario;


    public VistaJuego() {
        setTitle("Simulador de Supervivencia");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        add(layeredPane, BorderLayout.CENTER);

        lblInstrucciones = new JLabel("Haz clic en 'Iniciar' para comenzar la simulacion de supervivencia.");
        lblInstrucciones.setHorizontalAlignment(JLabel.CENTER);
        lblInstrucciones.setBounds(0, 0, 740, 500);
        layeredPane.add(lblInstrucciones, JLayeredPane.PALETTE_LAYER); // capa superior

        lblSalud = new JLabel("Salud: 100/100");
        barraSalud = new JProgressBar(0, 100);
        barraSalud.setPreferredSize(new Dimension(150, 15));
        barraSalud.setForeground(Color.GREEN);
        barraSalud.setValue(100);

        lblEnergia = new JLabel("Energia: 100/100");
        barraEnergia = new JProgressBar(0, 100);
        barraEnergia.setPreferredSize(new Dimension(150, 15));
        barraEnergia.setForeground(Color.GREEN);
        barraEnergia.setValue(100);

        // Establecer un tamaÃ±o preferido y mÃ¡ximo para las barras de salud y energÃ­a
        Dimension barSize = new Dimension(200, 15); // Puedes ajustar el "200" segÃºn el tamaÃ±o que desees
        barraSalud.setPreferredSize(barSize);
        barraSalud.setMaximumSize(barSize); // Esto evitarÃ¡ que la barra se expanda mÃ¡s allÃ¡ de 200x15
        barraEnergia.setPreferredSize(barSize);
        barraEnergia.setMaximumSize(barSize); // Esto evitarÃ¡ que la barra se expanda mÃ¡s allÃ¡ de 200x15

        btnIniciarJuego = new JButton("Iniciar Juego");
        add(btnIniciarJuego, BorderLayout.SOUTH);

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setVisible(false);

        lblEscenario = new JLabel();
        lblEscenario.setVisible(false);
        lblEscenario.setHorizontalAlignment(JLabel.CENTER);
        lblEscenario.setVerticalAlignment(JLabel.TOP);
        lblEscenario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblImagenEscenario = new JLabel();
        lblImagenEscenario.setHorizontalAlignment(JLabel.CENTER);
        lblImagenEscenario.setVerticalAlignment(JLabel.BOTTOM); // Cambio para que la imagen estÃ© debajo del texto

        panelOpciones = new JPanel(new GridLayout(0, 1));
        panelOpciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel central que contendrÃ¡ el escenario y la imagen del escenario
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.add(lblEscenario);
        panelCentral.add(lblImagenEscenario);
        panelCentral.setBounds(0, 0, 800, 600);
        layeredPane.add(panelCentral, JLayeredPane.DEFAULT_LAYER); // capa inferior

        // Panel izquierdo para las opciones
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BorderLayout());
        panelIzquierdo.add(panelOpciones, BorderLayout.SOUTH); // Las opciones se aÃ±aden al SUR para que estÃ©n en la parte inferior del panel izquierdo
        add(panelIzquierdo, BorderLayout.WEST);

        //...
        panelTop = new JPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));  // Cambio a BoxLayout en el eje Y

        // Agregar componentes al panelTop
        panelTop.add(lblSalud);
        panelTop.add(barraSalud);
        panelTop.add(lblEnergia);
        panelTop.add(barraEnergia);
        panelTop.add(lblEscenario);  // Agregar lblEscenario debajo de las barras

        add(panelTop, BorderLayout.NORTH);
        //...

    }

    public void moveToTop() {
        lblInstrucciones.setVisible(false);
        lblEscenario.setVisible(true);
        panelOpciones.setVisible(true);
        btnIniciarJuego.setVisible(false);
        btnSiguiente.setVisible(true);
        add(btnSiguiente, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    public void setImagenEscenario(String rutaImagenAbsoluta) {
        ImageIcon icono = new ImageIcon(rutaImagenAbsoluta);
        lblImagenEscenario.setIcon(icono);
        lblImagenEscenario.setVisible(true);
    }

    public JLabel getLblEscenario() {
        return lblEscenario;
    }

    public JLabel getLblInstrucciones() {
        return lblInstrucciones;
    }

    public JLabel getLblSalud() {
        return lblSalud;
    }

    public JLabel getLblEnergia() {
        return lblEnergia;
    }

    public JProgressBar getBarraSalud() {
        return barraSalud;
    }

    public JProgressBar getBarraEnergia() {
        return barraEnergia;
    }

    public JButton getBtnIniciarJuego() {
        return btnIniciarJuego;
    }

    public JButton getBtnSiguiente() {
        return btnSiguiente;
    }

    public JPanel getPanelOpciones() {
        return panelOpciones;
    }

    public void setEscenarioTexto(String texto) {
        lblEscenario.setText("<html>" + texto + "</html>");
    }

    public void setOpciones(String[] opciones) {
        panelOpciones.removeAll();
        ButtonGroup grupoOpciones = new ButtonGroup();

        for (String opcion : opciones) {
            JRadioButton radioButton = new JRadioButton(opcion);
            radioButton.setActionCommand(opcion);
            grupoOpciones.add(radioButton);
            panelOpciones.add(radioButton);
        }

        panelOpciones.revalidate();
        panelOpciones.repaint();
    }

    public String getOpcionSeleccionada() {
        for (Component comp : panelOpciones.getComponents()) {
            if (comp instanceof JRadioButton) {
                JRadioButton radioButton = (JRadioButton) comp;
                if (radioButton.isSelected()) {
                    return radioButton.getActionCommand();
                }
            }
        }
        return null;
    }
}
