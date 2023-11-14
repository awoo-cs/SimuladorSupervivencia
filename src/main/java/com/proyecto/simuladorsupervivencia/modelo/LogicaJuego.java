/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.simuladorsupervivencia.modelo;

/**
 *
 * @author q-ql
 */
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LogicaJuego {
    private List<Escenario> escenarios;
    private int escenarioActual;
    private int puntosDeDecision;
    private int energia;
    private int salud;
    private List<DecisionJugador> decisionesJugador = new ArrayList<>();

    public LogicaJuego() {
        escenarioActual = -1;// Indica que no hay ningÃºn escenario activo al inicio
        puntosDeDecision = 20;
        energia = 100;
        salud  = 100;

        cargarEscenarios();
        reiniciarJuego();
    }

    public Escenario getEscenarioActual() {
        if (escenarioActual >= 0 && escenarioActual < escenarios.size()) {
            return escenarios.get(escenarioActual);
        } else {
            return null; // O manejar de otra manera
        }
    }

    public void siguienteEscenario() {
        if (escenarioActual < escenarios.size() - 1) {
            escenarioActual++;
        }else{
            finDelJuego();
        }
    }

    
    /*private void mostrarEstadisticasJugador() {
        String[] columnNames = {"DecisiÃ³n", "DescripciÃ³n", "Puntos"};

        // TODO: Replace with actual data
        Object[][] data = {
            {"OpciÃ³n 1", "Buena elecciÃ³n para mantenerse seguro", "10"},
            {"OpciÃ³n 2", "Esfuerzo vale la pena, pero es agotador", "7"},
            {"OpciÃ³n 3", "Puede ser peligroso", "5"}
        };

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JOptionPane.showMessageDialog(null, scrollPane, "EstadÃ­sticas del Jugador", JOptionPane.INFORMATION_MESSAGE);
    }*/

    /*private void mostrarEstadisticasJugador() {
        String[] columnNames = {"DecisiÃ³n", "DescripciÃ³n", "Puntos"};

        List<DecisionJugador> decisiones = getDecisionesJugador();
        Object[][] data = new Object[decisiones.size()][3];
        for (int i = 0; i < decisiones.size(); i++) {
            DecisionJugador decision = decisiones.get(i);
            data[i][0] = decision.getDecision();
            data[i][1] = decision.getDescripcion();
            data[i][2] = String.valueOf(decision.getPuntos());
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JOptionPane.showMessageDialog(null, scrollPane, "EstadÃ­sticas del Jugador", JOptionPane.INFORMATION_MESSAGE);
    }*/

    private void mostrarEstadisticasJugador() {
        String[] columnNames = {"Decision", "Descripcion", "Puntos"};
        List<DecisionJugador> decisiones = getDecisionesJugador();
        Object[][] data = new Object[decisiones.size() + 2][3];

        int puntajeTotal = 20;

        for (int i = 0; i < decisiones.size(); i++) {
            DecisionJugador decision = decisiones.get(i);
            data[i][0] = decision.getDecision();
            data[i][1] = decision.getDescripcion();
            data[i][2] = String.valueOf(decision.getPuntos());
            puntajeTotal += decision.getPuntos();
        }

        data[decisiones.size()][0] = "Puntaje Total";
        data[decisiones.size()][1] = "";
        data[decisiones.size()][2] = puntajeTotal;

        String rango;
        if (puntajeTotal <= 13) {
            rango = "Aprendiz";
        } else if (puntajeTotal <= 27) {
            rango = "Novato";
        } else {
            rango = "Sobreviviente";
        }

        data[decisiones.size() + 1][0] = "Rango";
        data[decisiones.size() + 1][1] = "";
        data[decisiones.size() + 1][2] = rango;

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Esto hace que todas las celdas no sean editables
            }
        };

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        // Hacer "Puntaje Total" y "Rango" en negrita
        DefaultTableCellRenderer boldRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row >= decisiones.size()) {
                    setFont(getFont().deriveFont(Font.BOLD));
                } else {
                    setFont(getFont().deriveFont(Font.PLAIN));
                }
                return this;
            }
        };

        table.getColumnModel().getColumn(0).setCellRenderer(boldRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(boldRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(boldRenderer);

        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "Estadisticas del Jugador", JOptionPane.INFORMATION_MESSAGE);
    }


    private void finDelJuego(){
        //JOptionPane.showMessageDialog(null, "Â¡Fin del juego! Gracias por jugar.");

        int respuesta = JOptionPane.showConfirmDialog(null, "¿Te gustaria jugar de nuevo?", "Juego Terminado", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            reiniciarJuego();
        } else {
            mostrarEstadisticasJugador(); // Show the JTable
            System.exit(0); // Cierra la aplicaciÃ³n
        }

        //String estadisticas = obtenerEstadisticasDelJugador();
        //JOptionPane.showMessageDialog(null, estadisticas, "EstadÃ­sticas finales", JOptionPane.INFORMATION_MESSAGE);
    }

    public void reiniciarJuego(){
        escenarioActual = 0;
        salud = 100;
        energia = 100;
        puntosDeDecision = 20;
    }

    //public void procesarOpcion(String opcion){

    //}

    public void procesarDecision(String decision) {
        switch(escenarioActual) {
            case 0: // EstÃ¡s perdido en un bosque espeso despuÃ©s de una excursiÃ³n. La noche estÃ¡ cayendo.
                if ("Hacer una fogata para la noche".equals(decision)) {
                    ajustarSalud(2);//Calor y sensaciÃ³n de seguridad
                    ajustarEnergia(-5);//Esfuerzo para hacer la fogata
                    puntosDeDecision += 2;// Buena elecciÃ³n para mantenerse seguro
                    decisionesJugador.add(new DecisionJugador(decision, "Buena eleccion para mantenerse seguro", 2));
                } else if ("Construir un refugio improvisado".equals(decision)) {
                    ajustarEnergia(-10);//Mayor esfuerzo que hacer una fogata
                    puntosDeDecision += 1;//Esfuerzo vale la pena, pero es agotador
                    decisionesJugador.add(new DecisionJugador(decision, "Esfuerzo vale la pena, pero es agotador", 1));
                } else if ("Continuar caminando a pesar de la oscuridad".equals(decision)) {
                    ajustarSalud(-5);//Riesgo de lesiones
                    ajustarEnergia(-15);//Caminar es agotador, especialmente en la oscuridad
                    puntosDeDecision -= 1;//Puede ser peligroso
                    decisionesJugador.add(new DecisionJugador(decision, "Puede ser peligroso", -1));
                }
                break;
            case 1: // Oyes el sonido distante de agua corriente.
                if ("Seguir el sonido del agua".equals(decision)) {
                    ajustarEnergia(-5);//Caminar en busca del sonido
                    puntosDeDecision += 2;//El agua es vital
                    decisionesJugador.add(new DecisionJugador(decision, "El agua es vital", 2));
                } else if ("Ir en direccion opuesta".equals(decision)) {
                    ajustarEnergia(-5);//Caminar en direccion opuesta
                    puntosDeDecision -= 1;//Alejarse del agua puede no ser una buena idea
                    decisionesJugador.add(new DecisionJugador(decision, "Alejarse del agua puede no ser una buena idea", -1));
                } else if ("Acampar aqui por la noche".equals(decision)) {
                    ajustarEnergia(5);//Descansar es bueno
                    puntosDeDecision += 1;//Es prudente descansar cuando es necesario
                    decisionesJugador.add(new DecisionJugador(decision, "Es prudente descansar cuando es necesario", 1));
                }
                break;
            case 2: // Encuentras un rÃ­o con agua clara que fluye.
                if ("Beber agua directamente del rio".equals(decision)) {
                    ajustarSalud(-10);//Riesgo de Enfermedades
                    puntosDeDecision -= 1;//Beber agua sin purificar puede ser peligroso
                    decisionesJugador.add(new DecisionJugador(decision, "Beber agua sin purificar puede ser peligroso", -1));
                } else if ("Usar una tecnica de purificacion".equals(decision)) {
                    ajustarSalud(5);//Agua purificada es buena
                    ajustarEnergia(-5);//Esfuerzo para purificar el agua
                    puntosDeDecision += 2;//Accion mas segura
                    decisionesJugador.add(new DecisionJugador(decision, "Accion mas segura", 2));
                } else if ("Ignorar el rio porque no confias en el agua".equals(decision)) {
                    ajustarEnergia(-10);//Perder la oportunidad de hidratarse
                    puntosDeDecision -= 2;//Ignorar una fuente potencial de agua no es ideal
                    decisionesJugador.add(new DecisionJugador(decision, "Ignorar una fuente potencial de agua no es ideal", -2));
                }
                break;
            case 3://La temperatura cae rÃ¡pidamente y empiezas a sentir frÃ­o.
                if ("Reunir madera seca para hacer una fogata".equals(decision)) {
                    ajustarSalud(5);//Calor y sensacion de seguridad
                    ajustarEnergia(-10);//Esfuerzo para reunir madera y hacer la fogata
                    puntosDeDecision += 2;//Buena eleccion para mantenerse caliente
                    decisionesJugador.add(new DecisionJugador(decision, "Buena eleccion para mantenerse caliente", 2));
                } else if ("Mantenerse en movimiento para mantenerse caliente".equals(decision)) {
                    ajustarEnergia(-15);//Caminar es agotador
                    puntosDeDecision += 1;//Es una forma de mantenerse caliente
                    decisionesJugador.add(new DecisionJugador(decision, "Es una forma de mantenerse caliente", 1));
                } else if ("Acampar sin fuego".equals(decision)) {
                    ajustarSalud(-10);//Riesgo de hipotermia
                    ajustarEnergia(-5);//Sin el beneficio del calor del fuego
                    puntosDeDecision -= 2;//Puede ser peligroso
                    decisionesJugador.add(new DecisionJugador(decision, "Puede ser peligroso", -2));
                }
                break;
            case 4://Descubres unas bayas en un arbusto. No estÃ¡s seguro de si son comestibles.
                if ("Comer las bayas".equals(decision)) {
                    if (Math.random() < 0.5) {
                        ajustarSalud(-20);//0% de probabilidad de perder 20 de salud si son tÃ³xicas
                    }else{
                        ajustarEnergia(10);//Si no son tÃ³xicas
                    }
                    puntosDeDecision -= 1;//Riesgoso sin conocimiento
                    decisionesJugador.add(new DecisionJugador(decision, "Riesgoso sin conocimiento", -1));
                } else if ("Guardarlas para mas tarde".equals(decision)) {
                    puntosDeDecision += 1;//Prudente, podrian ser utiles
                    decisionesJugador.add(new DecisionJugador(decision, "Prudente, podrian ser utiles", 1));
                } else if ("Ignorar las bayas y continuar".equals(decision)) {
                    ajustarEnergia(-5);
                    puntosDeDecision -= 3;//Oportunidad perdida de alimentarse
                    decisionesJugador.add(new DecisionJugador(decision, "Oportunidad perdida de alimentarse", -5));
                }
                break;
            case 5://Encuentras un sendero desgastado que parece llevar a algÃºn lugar.
                if ("Seguir el sendero".equals(decision)) {
                    ajustarEnergia(-5);//Caminar por el sendero
                    puntosDeDecision += 2;//Seguir un sendero puede llevar a la civilizaciÃ³n
                    decisionesJugador.add(new DecisionJugador(decision, "Seguir un sendero puede llevar a la civilizacion", 2));
                } else if ("Ignorarlo y adentrarte mas en el bosque".equals(decision)) {
                    ajustarEnergia(-10);//Caminar sin un camino claro
                    puntosDeDecision -= 2;//Alejarse de un posible camino hacia la seguridad
                    decisionesJugador.add(new DecisionJugador(decision, "Alejarse de un posible camino hacia la seguridad", -2));
                } else if ("Acampar cerca del sendero por la noche".equals(decision)) {
                    ajustarEnergia(5);//Descansar es bueno
                    puntosDeDecision += 1;//Es prudente descansar cuando es necesario
                    decisionesJugador.add(new DecisionJugador(decision, "Es prudente descansar cuando es necesario", 1));
                }
                break;
            case 6://Oyes un rugido a lo lejos. PodrÃ­a ser un animal salvaje.
                if ("Investigar el sonido".equals(decision)) {
                    if (Math.random() < 0.7){
                        ajustarSalud(-20);//70% de probabilidad de perder 20 de salud si encuentras un animal peligroso
                    }
                    ajustarEnergia(-10);//Investigar es agotador y estresante
                    puntosDeDecision -= 2;//Peligroso acercarse a un animal salvaje
                    decisionesJugador.add(new DecisionJugador(decision, "Peligroso acercarse a un animal salvaje", -2));
                } else if ("Huir del sonido".equals(decision)) {
                    ajustarEnergia(-15);//Huir es agotador
                    puntosDeDecision += 1;//Es prudente alejarse del peligro
                    decisionesJugador.add(new DecisionJugador(decision, "Es prudente alejarse del peligro", 1));
                } else if ("Subir a un arbol cercano y esperar".equals(decision)) {
                    ajustarEnergia(-5);//Subir a un Ã¡rbol requiere esfuerzo
                    puntosDeDecision += 2;//PosiciÃ³n segura y estratÃ©gica
                    decisionesJugador.add(new DecisionJugador(decision, "Posicion segura y estrategica", 2));
                }
                break;
            case 7://Encuentras una cueva. Parece profunda y oscura.
                if ("Explorar la cueva".equals(decision)) {
                    if (Math.random() < 0.5) {
                        ajustarSalud(-20);//50% de probabilidad de perder 20 de salud si hay un peligro dentro
                    }
                    ajustarEnergia(-10);//Explorar es agotador
                    puntosDeDecision -= 1;//Peligroso sin preparaciÃ³n
                    decisionesJugador.add(new DecisionJugador(decision, "Peligroso sin preparacion", -1));
                } else if ("Usar la entrada de la cueva como refugio".equals(decision)) {
                    ajustarSalud(5);//Proteccion contra alimentos
                    puntosDeDecision += 2;//Usar la naturaleza como refugio es inteligente
                    decisionesJugador.add(new DecisionJugador(decision, "Usar la naturaleza como refugio es inteligente", 2));
                } else if ("Ignorar la cueva y seguir adelante".equals(decision)) {
                    ajustarEnergia(-5);
                    puntosDeDecision -= 2;//Seguir adelante sin descansar
                    decisionesJugador.add(new DecisionJugador(decision, "Seguir adelante sin descansar", -2));
                }
                break;
            case 8://Encuentras una mochila abandonada en el camino.
                if ("Revisar la mochila en busca de suministros".equals(decision)) {
                    ajustarSalud(10);//Si encuentras suministros Ãºtiles
                    ajustarEnergia(10);//Si encuentras comida o agua
                    puntosDeDecision += 2;//Buscar suministros es esencial para la supervivencia
                    decisionesJugador.add(new DecisionJugador(decision, "Buscar suministros es esencial para la supervivencia", 2));
                } else if ("Ignorar la mochila, podria ser peligroso".equals(decision)) {
                    puntosDeDecision -= 1;//Oportunidad perdida de encontrar suministros
                    decisionesJugador.add(new DecisionJugador(decision, "Oportunidad perdida de encontrar suministros", -1));
                } else if ("Llevar la mochila contigo sin revisarla".equals(decision)) {
                    ajustarEnergia(-5);
                    puntosDeDecision -= 1;//Llevar peso adicional es agotador
                    decisionesJugador.add(new DecisionJugador(decision, "Llevar peso adicional es agotador", -2));
                }
                break;
            case 9://DespuÃ©s de varios dÃ­as, finalmente ves una seÃ±al de humo a lo lejos.
                if("Dirigirse hacia la senial de humo".equals(decision)){
                    ajustarEnergia(-15);//Dirigirse hacia una posible seÃ±al de civilizaciÃ³n puede ser agotador, pero vale la pena el esfuerzo
                    puntosDeDecision += 4;//Es una fuerte indicaciÃ³n de civilizaciÃ³n o ayuda
                    decisionesJugador.add(new DecisionJugador(decision, "Es una fuerte indicacion de civilizacion o ayuda", 3));
                } else if ("Ignorarla y seguir tu camino".equals(decision)) {
                    ajustarEnergia(-10);//Continuar sin investigar la seÃ±al
                    puntosDeDecision -= 2;//Ignorar una posible seÃ±al de ayuda puede no ser la mejor decisiÃ³n
                    decisionesJugador.add(new DecisionJugador(decision, "Ignorar una posible senial de ayuda puede no ser la mejor decision", -2));
                } else if ("Esperar y recuperar mas energia antes de investigar".equals(decision)) {
                    ajustarEnergia(10);//Tomarse un descanso para recuperarse es bueno
                    puntosDeDecision += 1;//Es prudente asegurarse de estar en buena forma antes de investigar una situaciÃ³n desconocida
                    decisionesJugador.add(new DecisionJugador(decision, "Es prudente asegurarse de estar en buena forma antes de investigar una situacion desconocida", 1));
                }
        }
    }

    public int getPuntosDeDecision() {
        return puntosDeDecision;
    }

    public void addPuntosDeDecision(int puntos) {
        this.puntosDeDecision += puntos; // Asume que "puntos" puede ser negativo si es una penalizaciÃ³n
    }

    public int getEnergia() {
        return energia;
    }

    public void cambiarEnergia(int cambio) { // "cambio" puede aumentar o disminuir la energÃ­a
        this.energia += cambio;
        if (this.energia > 100) this.energia = 100; // Asegura que la energÃ­a no exceda el mÃ¡ximo
        if (this.energia < 0) this.energia = 0;     // Asegura que la energÃ­a no sea negativa
    }

    public int getSalud() {
        return salud;
    }

    public void cambiarSalud(int cambio) { // "cambio" puede aumentar o disminuir la salud
        this.salud += cambio;
        if (this.salud > 100) this.salud = 100; // Asegura que la salud no exceda el mÃ¡ximo
        if (this.salud < 0) this.salud = 0;     // Asegura que la salud no sea negativa
    }

    private void cargarEscenarios(){
        escenarios = new ArrayList<>();

        escenarios.add(new Escenario("Estas perdido en un bosque espeso despues de una excursion. La noche esta cayendo.",
                new String[]{"Hacer una fogata para la noche", "Construir un refugio improvisado", "Continuar caminando a pesar de la oscuridad"},
                "src\\main\\java\\img\\recursos\\escenario1\\1.png"));
        escenarios.add(new Escenario("Oyes el sonido distante de agua corriente.",
                new String[]{"Seguir el sonido del agua", "Ir en direccion opuesta", "Acampar aqui por la noche"},
                "src\\main\\java\\img\\recursos\\escenario2\\1.png"));
        escenarios.add(new Escenario("Encuentras un rio con agua clara que fluye.",
                new String[]{"Beber agua directamente del rio", "Usar una tecnica de purificacion", "Ignorar el rio porque no confias en el agua"},
                "src\\main\\java\\img\\recursos\\escenario3\\1.png"));
        escenarios.add(new Escenario("La temperatura cae rapidamente y empiezas a sentir frio.",
                new String[]{"Reunir madera seca para hacer una fogata", "Mantenerse en movimiento para mantenerse caliente", "Acampar sin fuego"},
                "src\\main\\java\\img\\recursos\\escenario4\\1.png"));
        escenarios.add(new Escenario("Descubres unas bayas en un arbusto. No estas seguro de si son comestibles.",
                new String[]{"Comer las bayas", "Guardarlas para mas tarde", "Ignorar las bayas y continuar"},
                "src\\main\\java\\img\\recursos\\escenario5\\1.png"));
        escenarios.add(new Escenario("Encuentras un sendero desgastado que parece llevar a algun lugar.",
                new String[]{"Seguir el sendero", "Ignorarlo y adentrarte mas en el bosque", "Acampar cerca del sendero por la noche"},
                "src\\main\\java\\img\\recursos\\escenario6\\1.png"));
        escenarios.add(new Escenario("Oyes un rugido a lo lejos. Podria ser un animal salvaje.",
                new String[]{"Investigar el sonido", "Huir del sonido", "Subir a un arbol cercano y esperar"},
                "src\\main\\java\\img\\recursos\\escenario7\\1.png"));
        escenarios.add(new Escenario("Encuentras una cueva. Parece profunda y oscura.",
                new String[]{"Explorar la cueva", "Usar la entrada de la cueva como refugio", "Ignorar la cueva y seguir adelante"},
                "src\\main\\java\\img\\recursos\\escenario8\\1.png"));
        escenarios.add(new Escenario("Encuentras una mochila abandonada en el camino.",
                new String[]{"Revisar la mochila en busca de suministros", "Ignorar la mochila, podria ser peligroso", "Llevar la mochila contigo sin revisarla"},
                "src\\main\\java\\img\\recursos\\escenario9\\1.png"));
        escenarios.add(new Escenario("Despues de varios dias, finalmente ves una senial de humo a lo lejos.",
                new String[]{"Dirigirse hacia la senial de humo", "Ignorarla y seguir tu camino", "Esperar y recuperar mas energia antes de investigar"},
                "src\\main\\java\\img\\recursos\\escenario10\\1.png"));
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    private void ajustarSalud(int cambio){
        salud += cambio;
        if(salud > 100){
            salud = 100;
        } else if (salud < 0) {
            salud = 0;
        }
    }

    private void ajustarEnergia(int cambio){
        energia += cambio;
        if(energia > 100){
            energia = 100;
        } else if (energia < 0) {
            energia = 0;
        }
    }

    public List<DecisionJugador> getDecisionesJugador() {
        return decisionesJugador;
    }

    public void setDecisionesJugador(List<DecisionJugador> decisionesJugador) {
        this.decisionesJugador = decisionesJugador;
    }
}
