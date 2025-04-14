package org.example.Vista;



import org.example.Modelo.Paquetebasico;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ComparacionVista extends JFrame {

    public ComparacionVista(List<Paquetebasico> paquetes) {
        setTitle("Comparación de Paquetes");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, paquetes.size()));

        for (Paquetebasico p : paquetes) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createTitledBorder(p.getDestino()));

            panel.add(new JLabel("Precio: $" + p.getPrecio()));
            panel.add(new JLabel("Duración: " + p.getDuracionDias() + " días"));
            panel.add(new JLabel("<html>Servicios:<br>" + String.join("<br>", p.getServicios()) + "</html>"));

            add(panel);
        }
    }
}
