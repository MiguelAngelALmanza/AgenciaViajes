package org.example.Vista;

import org.example.Modelo.Paquetebasico;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PaqueteVista extends JFrame {
    private JCheckBox[] checkboxes;
    private JButton botonComparar;

    public PaqueteVista(List<Paquetebasico> paquetes) {
        setTitle("Explora nuestros paquetes turísticos");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panelPaquetes = new JPanel();
        panelPaquetes.setLayout(new BoxLayout(panelPaquetes, BoxLayout.Y_AXIS));

        checkboxes = new JCheckBox[paquetes.size()];
        for (int i = 0; i < paquetes.size(); i++) {
            Paquetebasico p = paquetes.get(i);
            String texto = "<html><b>" + p.getDestino() + "</b><br>Precio: $" + p.getPrecio() +
                    "<br>Duración: " + p.getDuracionDias() + " días<br>Servicios: " +
                    String.join(", ", p.getServicios()) + "</html>";
            checkboxes[i] = new JCheckBox(texto);
            panelPaquetes.add(checkboxes[i]);
            panelPaquetes.add(Box.createVerticalStrut(10));
        }

        botonComparar = new JButton("Comparar Paquetes");

        JScrollPane scroll = new JScrollPane(panelPaquetes);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scroll, BorderLayout.CENTER);
        add(botonComparar, BorderLayout.SOUTH);
    }

    public JCheckBox[] getCheckboxes() {
        return checkboxes;
    }

    public JButton getBotonComparar() {
        return botonComparar;
    }
}

