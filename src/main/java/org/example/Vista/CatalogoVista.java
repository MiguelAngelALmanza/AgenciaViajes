package org.example.Vista;

import org.example.Controlador.CatalogoControlador;

import javax.swing.*;
import java.awt.*;

public class CatalogoVista extends JFrame {

    private JTextArea areaDestinos;
    private JButton botonRecargar;
    private JButton botonVerDetalles;
    private JButton botonVerReseñas; // NUEVO BOTÓN
    private JButton botonColaboradores;
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
        botonVerDetalles = new JButton("Ver Detalles");
        botonVerReseñas = new JButton("Ver Reseñas"); // Instanciar nuevo botón
        botonColaboradores = new JButton("Conoce a nuestros colaboradores"); // NUEVO

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonRecargar);
        panelBotones.add(botonVerDetalles);
        panelBotones.add(botonVerReseñas); // Agregarlo al panel
        panelBotones.add(botonColaboradores); // NUEVO

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public void mostrarDestinos(String texto) {
        areaDestinos.setText(texto);
    }

    public void setControlador(CatalogoControlador controlador) {
        botonRecargar.addActionListener(e -> controlador.cargarDestinos());
        botonVerDetalles.addActionListener(e -> controlador.verDetallesSeleccionado());
        botonVerReseñas.addActionListener(e -> controlador.verReseñasSeleccionadas()); // Nueva acción
        botonColaboradores.addActionListener(e -> {
            new org.example.Guiaturistico.Guiaturistico().setVisible(true);
        });

    }

    public JTextArea getAreaDestinos() {
        return areaDestinos;
    }
}