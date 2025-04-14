package org.example.Vista;

import org.example.Controlador.CatalogoControlador;

import javax.swing.*;
import java.awt.*;

public class CatalogoVista extends JFrame {

    private JTextArea areaDestinos;
    private JButton botonRecargar;

    public CatalogoVista() {
        setTitle("Catálogo de Destinos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        areaDestinos = new JTextArea();
        areaDestinos.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaDestinos);

        botonRecargar = new JButton("Recargar Catálogo");

        add(scroll, BorderLayout.CENTER);
        add(botonRecargar, BorderLayout.SOUTH);
    }

    public void mostrarDestinos(String texto) {
        areaDestinos.setText(texto);
    }

    public void setControlador(CatalogoControlador controlador) {
        botonRecargar.addActionListener(e -> controlador.cargarDestinos());
    }
}

